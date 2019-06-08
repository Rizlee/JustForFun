package com.rizlee.data.repository

import android.content.Context
import com.rizlee.data.R
import com.rizlee.data.network.api.RestApiService
import com.rizlee.domain.repository.IRepository
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject
import kotlin.random.Random


private const val DELIMITER = "|"

private const val CURRENT_DAY_POSITION = 0
private const val CURRENT_TIME_POSITION = 1

private const val APHORISM_POSITION = 0

class Repository @Inject constructor(private val context: Context, private val restApiService: RestApiService) :
    IRepository {

    private val dateFormat by lazy { SimpleDateFormat("dd-MM-yyyy", Locale.US) }
    private val timeFormat by lazy { SimpleDateFormat("HH:mm:ss", Locale.US) }

    private val cityPattern by lazy {
        Pattern.compile(
            context.getString(R.string.question_4_2),
            Pattern.CASE_INSENSITIVE
        )
    }

    private val easyQuestions by lazy { context.resources.getStringArray(R.array.questions_easy) }
    private val easyAswers by lazy { context.resources.getStringArray(R.array.answers_easy) }

    private val mediumQuestions by lazy { context.resources.getStringArray(R.array.questions_medium) }

    private val hardQuestions by lazy { context.resources.getStringArray(R.array.questions_hard) }

    private val defaultAnswers by lazy { context.getString(R.string.answer_default) }

    private fun getRandomAnswer(answers: String): String {
        val answersCount: Int
        return answers.split(DELIMITER).apply { answersCount = size }[Random.nextInt(0, answersCount)]
    }

    override fun getAnswer(question: String): Single<String> {

        for (i in easyQuestions.indices) {
            if (easyQuestions[i].contains(question, true)) {
                return Single.just(getRandomAnswer(easyAswers[i]))
            }
        }

        for (i in mediumQuestions.indices) {
            if (mediumQuestions[i].contains(question, true))
                return Single.just(
                    when (i) {
                        CURRENT_DAY_POSITION -> dateFormat.format(Date())
                        CURRENT_TIME_POSITION -> timeFormat.format(Date())
                        else -> getRandomAnswer(defaultAnswers)
                    }
                )
        }

        for (i in hardQuestions.indices) {
            if (hardQuestions[i].contains(question, true))
                return when (i) {
                    APHORISM_POSITION -> restApiService.getRandomAphorism()
                        .map { "${it.text} ${context.getString(R.string.answer_3_1_author)} ${it.author}" }
                    else -> Single.just(getRandomAnswer(defaultAnswers))
                }
        }

        cityPattern.matcher(question).apply {
            if (this.find()) {
                return restApiService.getWeather(this.group(1))
                    .map {
                        it.body?.let { body ->
                            String.format(
                                context.getString(R.string.answer_4_2_current),
                                body.temperature
                            )
                        } ?: run { context.getString(R.string.answer_4_2_unknown_location) }
                    }.onErrorReturn { context.getString(R.string.answer_4_2_unknown_location) }
            }
        }

        return Single.just(getRandomAnswer(defaultAnswers))
    }

}
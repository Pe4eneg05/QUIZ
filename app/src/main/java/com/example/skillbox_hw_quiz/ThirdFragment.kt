package com.example.skillbox_hw_quiz

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentThirdBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argFirst = arguments?.getInt("firstArg")//arguments?.getInt("firstArg")
        val argSecond = arguments?.getInt("secondArg")
        val argThird = arguments?.getInt("thirdArg")
        val listAnswer = mutableListOf(argFirst, argSecond, argThird)

        val quiz = QuizStorage
        val localization = Locale.getDefault().language
        var feedback = quiz.answer(quiz.getQuiz(QuizStorage.Locale.En), listAnswer)
        if (localization == "ru") feedback =
            quiz.answer(quiz.getQuiz(QuizStorage.Locale.Ru), listAnswer)

        val listFeedback = feedback.split(". ")
        binding.textAnswer1.text = listFeedback[0]
        binding.textAnswer2.text = listFeedback[1]
        binding.textAnswer3.text = listFeedback[2]

        ObjectAnimator.ofArgb(
            binding.textAnswer1,
            "BackgroundColor",
            Color.parseColor("#0000FF"),
            Color.parseColor("#FF0000")
        )
            .apply {
                duration = 4000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = 4
                repeatMode = ObjectAnimator.REVERSE
            }.start()

        ObjectAnimator.ofArgb(
            binding.textAnswer2,
            "BackgroundColor",
            Color.parseColor("#33FF00"),
            Color.parseColor("#FF9900")
        )
            .apply {
                duration = 4000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = 4
                repeatMode = ObjectAnimator.REVERSE
            }.start()

        ObjectAnimator.ofArgb(
            binding.textAnswer3,
            "BackgroundColor",
            Color.parseColor("#6600FF"),
            Color.parseColor("#CCFFFF")
        )
            .apply {
                duration = 4000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = 4
                repeatMode = ObjectAnimator.REVERSE
            }.start()

        binding.buttonThird.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
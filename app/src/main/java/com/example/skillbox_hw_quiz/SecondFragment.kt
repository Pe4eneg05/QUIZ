package com.example.skillbox_hw_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentSecondBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val localization = Locale.getDefault().language
        var list = QuizStorage.getQuiz(QuizStorage.Locale.En)
        if (localization == "ru") list = QuizStorage.getQuiz(QuizStorage.Locale.Ru)


        //Работа со первым блоком RadioGroup
        binding.textFirstQuestion.text = list.questions[0].question
        binding.question1FirstAnswer.text = list.questions[0].answers[0]
        binding.question1SecondAnswer.text = list.questions[0].answers[1]
        binding.question1ThirdAnswer.text = list.questions[0].answers[2]
        binding.question1FourthAnswer.text = list.questions[0].answers[3]

        //Работа со вторым блоком RadioGroup
        binding.textSecondQuestion.text = list.questions[1].question
        binding.question2FirstAnswer.text = list.questions[1].answers[0]
        binding.question2SecondAnswer.text = list.questions[1].answers[1]
        binding.question2ThirdAnswer.text = list.questions[1].answers[2]
        binding.question2FourthAnswer.text = list.questions[1].answers[3]

        //Работа с третьим блоком RadioGroup
        binding.textThirdQuestion.text = list.questions[2].question
        binding.question3FirstAnswer.text = list.questions[2].answers[0]
        binding.question3SecondAnswer.text = list.questions[2].answers[1]
        binding.question3ThirdAnswer.text = list.questions[2].answers[2]
        binding.question3FourthAnswer.text = list.questions[2].answers[3]

        //Присвоение прозрачности 0 элементам фрагмента
        binding.radioGroupFirst.alpha = 0f
        binding.radioGroupSecond.alpha = 0f
        binding.radioGroupThird.alpha = 0f
        binding.textFirstQuestion.alpha = 0f
        binding.textSecondQuestion.alpha = 0f
        binding.textThirdQuestion.alpha = 0f
        binding.buttonOnThirdFragment.alpha = 0f

        //Анимании элементов первого вопроса
        binding.textFirstQuestion.animate().apply {
            duration = 1000
            alpha(1f)
        }.start()
        binding.radioGroupFirst.animate().apply {
            duration = 1000
            alpha(1f)
        }.start()

        //Анимании элементов второго вопроса
        binding.radioGroupFirst.setOnCheckedChangeListener { _, _ ->
            binding.textSecondQuestion.animate().apply {
                duration = 1000
                alpha(1f)
            }.start()
            binding.radioGroupSecond.animate().apply {
                duration = 1000
                alpha(1f)
            }.start()
        }

        //Анимании элементов третьего вопроса
        binding.radioGroupSecond.setOnCheckedChangeListener { _, _ ->
            binding.textThirdQuestion.animate().apply {
                duration = 1000
                alpha(1f)
            }.start()
            binding.radioGroupThird.animate().apply {
                duration = 1000
                alpha(1f)
            }.start()
        }

        //Анимании кнопки
        binding.radioGroupThird.setOnCheckedChangeListener { _, _ ->
            binding.buttonOnThirdFragment.animate().apply {
                duration = 1000
                alpha(1f)
            }.start()
        }

        //Передача аргументов в следующий фрагмент
        binding.buttonOnThirdFragment.setOnClickListener {
            val firstId = binding.radioGroupFirst.checkedRadioButtonId
            val secondId = binding.radioGroupSecond.checkedRadioButtonId
            val thirdId = binding.radioGroupThird.checkedRadioButtonId
            val bundle = Bundle()
            val firstIndex: Int =
                binding.radioGroupFirst.indexOfChild(binding.radioGroupFirst.findViewById(firstId))
            val secondIndex: Int =
                binding.radioGroupSecond.indexOfChild(binding.radioGroupSecond.findViewById(secondId))
            val thirdIndex: Int =
                binding.radioGroupThird.indexOfChild(binding.radioGroupThird.findViewById(thirdId))
            if (firstId != -1) {
                bundle.putInt("firstArg", firstIndex)
            } else bundle.putString("firstArg", "Вы ничего не выбрали!")
            if (secondId != -1) {
                bundle.putInt("secondArg", secondIndex)
            } else bundle.putString("secondArg", "Вы ничего не выбрали!")
            if (thirdId != -1) {
                bundle.putInt("thirdArg", thirdIndex)
            } else bundle.putString("thirdArg", "Вы ничего не выбрали!")

            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
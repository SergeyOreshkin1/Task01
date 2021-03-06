package com.example.astontask01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.astontask01.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var elementPosition: String

    companion object {

        private const val KEY_ELEMENT_POSITION = "ELEMENT_POSITION"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"

        fun newInstance(elementPosition: String, description: String): DetailsFragment {
            val args = bundleOf(
                KEY_ELEMENT_POSITION to elementPosition,
                KEY_DESCRIPTION to description)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val description = arguments?.getString(KEY_DESCRIPTION)
       elementPosition = "Был закрыт элемент с позицией: " + arguments?.getString(KEY_ELEMENT_POSITION)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).navigateToFragment(MainFragment.newInstance(elementPosition))
            }
        })

        binding.apply {

            descriptionText.text = description
            buttonBack.setOnClickListener {

               (activity as MainActivity).navigateToFragment(MainFragment.newInstance(elementPosition))

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



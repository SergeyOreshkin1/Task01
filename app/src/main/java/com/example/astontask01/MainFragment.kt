package com.example.astontask01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astontask01.adapter.MusicGroupListAdapter
import com.example.astontask01.data.Model
import com.example.astontask01.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val adapter: MusicGroupListAdapter = MusicGroupListAdapter()

    private lateinit var modelList: MutableList<Model>

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    companion object {

        private const val KEY_ELEMENT_POS = "ELEMENT_POSITION"

        fun newInstance(elementPosition: String? = null): MainFragment {
            val args = bundleOf(
                KEY_ELEMENT_POS to elementPosition,
                )
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun setUpAdapter() {

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun populateList() {

        modelList = mutableListOf(
            Model(
                "Metallica",
                resources.getString(R.string.Metallica),
                "США",
                "Трэш-метал",
                R.drawable.metallica
            ),
            Model(
                "Avenged Sevenfold",
                resources.getString(R.string.AvengedSevenfold),
                "США",
                "Хэви-метал",
                R.drawable.avenged
            ),
            Model(
                "Rammstein",
                resources.getString(R.string.Rammstein),
                "Германия",
                "Индастриал-метал",
                R.drawable.rammstein
            ),
            Model(
                "Children of Bodom",
                resources.getString(R.string.ChildrenOfBodom),
                "Финляндия",
                "Мелодичный дэт-метал",
                R.drawable.children
            ),)
        adapter.submitList(modelList)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        populateList()
        setUpAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elementPosition = arguments?.getString(KEY_ELEMENT_POS)

        binding.apply {

            adapter.listener = { item,pos->
                (activity as MainActivity).navigateToFragment(DetailsFragment.newInstance(pos.toString(),item.description))
                println(pos.toString())

            }
            if (elementPosition != null) {
                elementPos.text = elementPosition
            }
        }
    }
}
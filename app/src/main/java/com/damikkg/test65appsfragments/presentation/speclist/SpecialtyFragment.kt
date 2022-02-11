package com.damikkg.test65appsfragments.presentation.speclist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.databinding.SpecialtyFragmentBinding
import com.damikkg.test65appsfragments.presentation.speclist.adapter.SpecialtyListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
@AndroidEntryPoint
class SpecialtyFragment : Fragment() {
    private var specialtyFragmentBinding: SpecialtyFragmentBinding? = null

    private val viewModel by viewModels<SpecialtyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.specialty_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = SpecialtyFragmentBinding.bind(view)
        specialtyFragmentBinding = binding

        val navController = activity?.findNavController(R.id.mainNavView)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                when(it)
                {
                    is SpecialtyViewModel.Status.Error ->
                    {
                        binding.sfStatusText.visibility = View.VISIBLE
                        binding.sfList.visibility = View.GONE

                        binding.sfStatusText.text = getString(R.string.error_text)
                    }
                    is SpecialtyViewModel.Status.Loading ->
                    {
                        binding.sfStatusText.visibility = View.VISIBLE
                        binding.sfList.visibility = View.GONE

                        binding.sfStatusText.text = getString(R.string.loading_text)
                    }
                    is SpecialtyViewModel.Status.Completed ->
                    {
                        binding.sfStatusText.visibility = View.GONE
                        binding.sfList.visibility = View.VISIBLE

                        binding.sfList.layoutManager = LinearLayoutManager(context)
                        binding.sfList.adapter = SpecialtyListAdapter(
                            it.result
                        )
                        {
                            s->
                            val action =
                                SpecialtyFragmentDirections.actionSpecialtyFragmentToPersonsListFragment(s)
                            navController?.navigate(action)
                        }
                    }
                    is SpecialtyViewModel.Status.NothingFound ->
                    {
                        binding.sfStatusText.visibility = View.VISIBLE
                        binding.sfList.visibility = View.GONE

                        binding.sfStatusText.text = getString(R.string.nothingfound_text)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        specialtyFragmentBinding = null
    }

}
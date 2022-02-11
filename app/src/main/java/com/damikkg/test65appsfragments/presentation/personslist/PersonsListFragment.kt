package com.damikkg.test65appsfragments.presentation.personslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.databinding.PersonsListFragmentBinding
import com.damikkg.test65appsfragments.presentation.personslist.adapter.PersonsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
@AndroidEntryPoint
class PersonsListFragment : Fragment() {
    private val args: PersonsListFragmentArgs by navArgs()
    private var personsListFragmentBinding:PersonsListFragmentBinding? = null

    private val viewModel by viewModels<PersonsListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.persons_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = activity?.findNavController(R.id.mainNavView)

        if(viewModel.state.value !is PersonsListViewModel.Status.Completed)
        {
            viewModel.getPersonsListBySpeciality(args.spec)
        }

        val binding = PersonsListFragmentBinding.bind(view)
        personsListFragmentBinding = binding

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                when(it)
                {
                    is PersonsListViewModel.Status.Error ->
                    {
                        binding.plfStateText.visibility = View.VISIBLE
                        binding.plfList.visibility = View.GONE

                        binding.plfStateText.text = getString(R.string.error_text)
                    }
                    is PersonsListViewModel.Status.Loading ->
                    {
                        binding.plfStateText.visibility = View.VISIBLE
                        binding.plfList.visibility = View.GONE

                        binding.plfStateText.text = getString(R.string.loading_text)
                    }
                    is PersonsListViewModel.Status.Completed ->
                    {
                        binding.plfStateText.visibility = View.GONE
                        binding.plfList.visibility = View.VISIBLE

                        binding.plfList.layoutManager = LinearLayoutManager(context)
                        binding.plfList.adapter = PersonsListAdapter(
                            it.result
                        )
                        { p->
                            val action = PersonsListFragmentDirections
                                .actionPersonsListFragmentToPersonFragment(p)

                            navController?.navigate(action)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personsListFragmentBinding = null
    }
}
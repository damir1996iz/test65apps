package com.damikkg.test65appsfragments.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.databinding.WelcomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private var welcomeViewBinding:WelcomeFragmentBinding? = null

    private val viewModel by viewModels<WelcomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WelcomeFragmentBinding.bind(view)
        welcomeViewBinding = binding

        val navController = activity?.findNavController(R.id.mainNavView)

        if(viewModel.state.value !is WelcomeViewModel.Status.Completed)
        {
            viewModel.updateDatabase()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                when(it)
                {
                    is WelcomeViewModel.Status.Error ->
                    {
                        binding.wfSubtitleTextView.text = getString(R.string.error_text)
                        binding.wfRetryBtn.visibility = View.VISIBLE
                        binding.wfIgnoreButton.visibility = View.VISIBLE
                    }
                    is WelcomeViewModel.Status.Loading ->
                    {
                        binding.wfSubtitleTextView.text = getString(R.string.loading_text)
                    }
                    is WelcomeViewModel.Status.Completed ->
                    {
                        navController?.navigate(R.id.action_welcomeFragment_to_specialtyFragment)
                    }
                }
            }
        }

        binding.wfRetryBtn.setOnClickListener {
            binding.wfRetryBtn.visibility = View.GONE
            binding.wfIgnoreButton.visibility = View.GONE
            viewModel.updateDatabase()
        }

        binding.wfIgnoreButton.setOnClickListener {
            navController?.navigate(R.id.action_welcomeFragment_to_specialtyFragment)
        }
    }

}
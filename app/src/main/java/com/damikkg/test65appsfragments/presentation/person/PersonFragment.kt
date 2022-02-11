package com.damikkg.test65appsfragments.presentation.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.databinding.PersonFragmentBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class PersonFragment : Fragment() {
    private val args:PersonFragmentArgs by navArgs()
    private var personFragmentBinding:PersonFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PersonFragmentBinding.bind(view)
        personFragmentBinding = binding

        val person = args.person

        binding.pfSpecs.text = person.speciality.joinToString(separator = "\n") {
            it.name
        }

        binding.pfName.text =
            getString(
                R.string.template_fname_sname,
                person.firstName.lowercase().replaceFirstChar { it.uppercase() },
                person.secondName.lowercase().replaceFirstChar { it.uppercase() }
            )

        if(person.birthDate != null)
        {
            binding.pfAgeAndYear.text =
                getString(R.string.template_year_age,
                    SimpleDateFormat("dd.MM.yyyy", Locale.US).format(person.birthDate),
                    person.getAge())
        }
        else
        {
            binding.pfAgeAndYear.visibility = View.GONE
        }

        if(person.avatarUrl.isNotEmpty())
        {
            Picasso.get()
                .load(person.avatarUrl)
                .placeholder(R.drawable.user)
                .resize(100,100)
                .into(binding.pfAvatar)
        }
        else
        {
            binding.pfAvatar.setImageResource(R.drawable.user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personFragmentBinding = null
    }
}
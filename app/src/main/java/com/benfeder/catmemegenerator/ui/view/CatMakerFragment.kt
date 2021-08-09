package com.benfeder.catmemegenerator.ui.view

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.benfeder.catmemegenerator.R
import com.benfeder.catmemegenerator.databinding.CatMakerFragmentBinding
import com.benfeder.catmemegenerator.ui.viewmodel.CatMakerViewModel
import com.example.mememaker.R
import com.example.mememaker.databinding.CatMakerFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import onTextViewChanged

@AndroidEntryPoint
class CatMakerFragment : Fragment(R.layout.cat_maker_fragment) {

    private val viewModel: CatMakerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CatMakerFragmentBinding.bind(view)
        with(binding){
            memeText.setText(viewModel.text)
            size.setText(viewModel.textSize.toString())

            memeText.addTextChangedListener {
                viewModel.text = it.toString()
            }
            color.addTextChangedListener {
                viewModel.textColor = it.toString()
            }
            filterSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.filter = parent?.getItemAtPosition(position).toString().lowercase()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            resultStyle.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: RadioButton = group.findViewById(checkedId)
                viewModel.mediaType = CatMakerViewModel.MediaType.valueOf(radioButton.text.toString().uppercase())

            }

            createButton.setOnClickListener {
                viewModel.getImage()
                findNavController().navigate(CatMakerFragmentDirections.actionCatMakerFragmentToCatViewFragment())
            }


        }


        viewModel.showTextAttributes.observe(viewLifecycleOwner){
            binding.apply {
                color.isVisible = it
                colorLabel.isVisible = it
                size.isVisible = it
                sizeLabel.isVisible = it
            }
        }
    }

}
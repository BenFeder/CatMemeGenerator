package com.benfeder.catmemegenerator.ui.view

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.benfeder.catmemegenerator.ui.viewmodel.CatMakerViewModel
import com.benfeder.catmemegenerator.utils.Resource
import com.benfeder.catmemegenerator.R
import com.benfeder.catmemegenerator.databinding.CatViewFragmentBinding
import com.benfeder.catmemegenerator.ui.viewmodel.CatMakerViewModel
import com.benfeder.catmemegenerator.utils.BASE_URL
import com.benfeder.catmemegenerator.utils.Resource
import com.benfeder.catmemegenerator.utils.View.loadImage
import com.example.mememaker.R
import com.example.mememaker.databinding.CatViewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import loadImage

@AndroidEntryPoint
class CatViewFragment : Fragment(R.layout.cat_view_fragment) {

    private val viewModel: CatMakerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CatViewFragmentBinding.bind(view)
        println(viewModel.showTextAttributes.value)
        with(binding) {
            viewModel.catImage.observe(viewLifecycleOwner, {
                println(it)
                when (it) {
                    is Resource.Success -> {
                        progressCircle.visibility = View.GONE
                        catResult.loadImage(BASE_URL + it.data.url.substring(1))
                    }

                    is Resource.Error -> {
                        progressCircle.visibility = View.GONE
                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_LONG).show()
                    }
                    Resource.Loading -> progressCircle.visibility = View.VISIBLE
                }
            }
            )

            createNewButton.setOnClickListener {
                viewModel.clearData()
                findNavController().navigate(CatViewFragmentDirections.actionCatViewFragmentToCatMakerFragment())
            }
        }


    }

}
package com.example.mvvmapihandle.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvmapihandle.R
import com.example.mvvmapihandle.databinding.FragmentFirstBinding
import com.example.mvvmapihandle.network.ApiService
import com.example.mvvmapihandle.network.RetrofitModule
import com.example.mvvmapihandle.utils.ApiResponse
import com.example.mvvmapihandle.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel:MainViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getPosts()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun getPosts() {


        lifecycleScope.launch {
            viewModel.data.collect{
                when(it){

                    is ApiResponse.Success ->{
                        Log.d("post","${it.data}")
                    }
                    is ApiResponse.Failure -> {
                        Log.d("post", it.msg)
                    }

                    ApiResponse.Loading ->{
                        Log.d("post","Loading....")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
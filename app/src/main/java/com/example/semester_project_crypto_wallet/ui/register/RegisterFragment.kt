package com.example.semester_project_crypto_wallet.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(){
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        registerViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(RegisterViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        binding.registerModel = registerViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.generateKeys.setOnClickListener{
            lifecycleScope.launch {
                registerViewModel.generateKeyPair()
            }
        }
//        binding.confirmRegister.setOnClickListener{
//            lifecycleScope.launch {
//                registerViewModel.confirmPin()
//            }
//        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmRegister.setOnClickListener {
            lifecycleScope.launch {
                registerViewModel.confirmPin()
            }
            it.findNavController().navigate(R.id.action_registerFragment_to_loggedInFragment)}
    }
}
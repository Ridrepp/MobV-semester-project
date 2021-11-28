package com.example.semester_project_crypto_wallet.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentPaymentBinding
import kotlinx.coroutines.launch

class PaymentFragment : Fragment(), AdapterView.OnItemSelectedListener{
    private lateinit var paymentsViewModel: PaymentsViewModel
    private lateinit var binding: FragmentPaymentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        paymentsViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(PaymentsViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_payment, container, false
        )
        binding.paymentsModel = paymentsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.sendButton.setOnClickListener{
            lifecycleScope.launch {
                paymentsViewModel.confirmPin()
            }
        }
        binding.contactsDropdown.onItemSelectedListener = this


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contacts: MutableList<String> = ArrayList()

        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this.requireContext(), R.layout.color_spinner_layout, contacts)


        dataAdapter.setDropDownViewResource(R.layout.color_spinner_layout)
        paymentsViewModel.receivers.observe(viewLifecycleOwner, { receiverName ->
            receiverName.forEach{
                dataAdapter.add(it.Name)
            }

        })
        binding.contactsDropdown.adapter = dataAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        return
    }
}
package com.teravision.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teravision.challenge.ui.UsersVM
import com.teravision.challenge.ui.observe
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SecondFragment : Fragment() {

    private val viewModel: UsersVM by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        observe(viewModel.user) {
            name.text = it.name
            username.text = it.username
            email.text = it.email
            street.text = it.address.street
            suite.text = it.address.suite
            city.text = it.address.city
            zipcode.text = it.address.zipcode
            lat.text = it.address.geo.lat
            lng.text = it.address.geo.lng
            phone.text = it.phone
            website.text = it.website
            company_name.text = it.company.name
            catch_phrase.text = it.company.catchPhrase
            bs.text = it.company.bs
        }
    }
}
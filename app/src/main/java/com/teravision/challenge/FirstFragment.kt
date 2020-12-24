package com.teravision.challenge

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teravision.challenge.domain.entity.UserEntity
import com.teravision.challenge.ui.UsersAdapter
import com.teravision.challenge.ui.UsersVM
import com.teravision.challenge.ui.observe
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FirstFragment : Fragment(), UsersAdapter.OnUserClickListener {

    private val viewModel: UsersVM by sharedViewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: UsersAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initLoading()

        checkInternetConnection()
    }

    override fun onOptionClicked(option: UserEntity) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        viewModel.itemClicked(option.id)
    }

    private fun initLoadingUsers() {
        observe(viewModel.users) {
            viewAdapter = UsersAdapter(this, it)
            recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = viewAdapter
            }
        }

        observe(viewModel.isErrorThrown) { isErrorActive ->
            if (isErrorActive) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("System Error")
                builder.setMessage("Something went wrong while fetching the data. Please, try again later.")
                builder.setPositiveButton("OK") { dialog,_ ->
                    dialog.dismiss()
                }
                builder.show()
                recyclerView.visibility = View.GONE
            }
        }
    }

    private fun checkInternetConnection() {
        observe(viewModel.hasConnectivity()) { hasConnection ->
            if (!hasConnection) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("No internet connection")
                builder.setMessage("You must be connected to the internet in order to use the app")
                builder.setPositiveButton("OK") { dialog,_ ->
                    dialog.dismiss()
                }
                recycler.visibility = View.GONE
                builder.show()
            } else {
                initLoadingUsers()
            }
        }
    }
}
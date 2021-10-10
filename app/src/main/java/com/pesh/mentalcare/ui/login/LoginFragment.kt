package com.pesh.mentalcare.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController

import com.pesh.mentalcare.R
import com.pesh.mentalcare.fragments.IllnessesTypesFragment
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_login, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)


        val usernameEdtLogin = view.findViewById<EditText>(R.id.edtLoginUserName)
        val passwordEdtLogin = view.findViewById<EditText>(R.id.edtLoginPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEdtLogin.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEdtLogin.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEdtLogin.text.toString(),
                    passwordEdtLogin.text.toString()
                )
            }
        }
        usernameEdtLogin.addTextChangedListener(afterTextChangedListener)
        passwordEdtLogin.addTextChangedListener(afterTextChangedListener)
        passwordEdtLogin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEdtLogin.text.toString(),
                    passwordEdtLogin.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            val fragment=IllnessesTypesFragment()
            val fragmentManager= activity?.supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.myNavHostFragment, fragment)
            transaction?.commit()


        }
    }


    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}

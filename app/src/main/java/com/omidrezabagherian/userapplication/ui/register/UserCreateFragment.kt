package com.omidrezabagherian.userapplication.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omidrezabagherian.userapplication.R
import com.omidrezabagherian.userapplication.databinding.FragmentUserCreateBinding
import com.omidrezabagherian.userapplication.model.CreateUser

class UserCreateFragment : Fragment(R.layout.fragment_user_create) {

    private lateinit var userCreateBanding: FragmentUserCreateBinding
    private val viewModel: UserCreateViewModel by viewModels()
    private val listHobbies = mutableListOf<String>()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userCreateBanding = FragmentUserCreateBinding.bind(view)

        saveCheckBox()

        userCreateBanding.buttonSend.setOnClickListener {
            val value = CreateUser(
                userCreateBanding.edittextName.text.toString(),
                userCreateBanding.edittextFamily.text.toString(),
                userCreateBanding.edittextNationalCode.text.toString(),
                listHobbies
            )

            viewModel.registerUser(value)

            viewModel.user.observe(viewLifecycleOwner) {
                Toast.makeText(context, "موفقیت آمیز بود", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.itemFragment)
            }

        }

    }

    private fun saveCheckBox() {
        userCreateBanding.checkboxCoding.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Coding")
            } else {
                listHobbies.remove("Coding")
            }
        }
        userCreateBanding.checkboxFootball.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Football")
            } else {
                listHobbies.remove("Football")
            }
        }
        userCreateBanding.checkboxSwimming.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Swimming")
            } else {
                listHobbies.remove("Swimming")
            }
        }
        userCreateBanding.checkboxCoding.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Coding")
            } else {
                listHobbies.remove("Coding")
            }
        }
        userCreateBanding.checkboxMovie.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Movie")
            } else {
                listHobbies.remove("Movie")
            }
        }
        userCreateBanding.checkboxFencing.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Fencing")
            } else {
                listHobbies.remove("Fencing")
            }
        }
        userCreateBanding.checkboxDigitalGames.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Digital games")
            } else {
                listHobbies.remove("Digital games")
            }
        }
        userCreateBanding.checkboxCooking.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Cooking")
            } else {
                listHobbies.remove("Cooking")
            }
        }
        userCreateBanding.checkboxPuzzles.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Puzzles")
            } else {
                listHobbies.remove("Puzzles")
            }
        }
        userCreateBanding.checkboxFishing.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Fishing")
            } else {
                listHobbies.remove("Fishing")
            }
        }
        userCreateBanding.checkboxHiking.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Hiking")
            } else {
                listHobbies.remove("Hiking")
            }
        }
        userCreateBanding.checkboxDrawing.setOnClickListener { check ->
            if (check.isClickable) {
                listHobbies.add("Drawing")
            } else {
                listHobbies.remove("Drawing")
            }
        }
    }
}
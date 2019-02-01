package fr.hetic.h3p2021.fragment


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt

import fr.hetic.h3p2021.R
import kotlinx.android.synthetic.main.fragment_color.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ColorFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(@ColorInt colorInt: Int) : ColorFragment {
            return ColorFragment().apply {
                this.color = colorInt
            }
        }

    }

    @ColorInt
    var color = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutFragment.setBackgroundColor(color)




    }


}

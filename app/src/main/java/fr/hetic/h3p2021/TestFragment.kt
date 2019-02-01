package fr.hetic.h3p2021


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import kotlinx.android.synthetic.main.fragment_test.*


class TestFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(@ColorInt colorInt: Int): TestFragment {
            return TestFragment().apply {
                this.colorInt = colorInt
            }
        }

    }

    @ColorInt
    var colorInt: Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_test, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        background_fragment.setBackgroundColor(colorInt)
    }


}

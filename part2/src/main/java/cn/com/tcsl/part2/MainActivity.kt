package cn.com.tcsl.part2

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val STATE_ZERO = intArrayOf(
            R.attr.state_zero, -R.attr.state_one, -R.attr.state_two
    )

    private val STATE_ONE = intArrayOf(
            -R.attr.state_zero, R.attr.state_one, -R.attr.state_two
    )

    private val STATE_TWO = intArrayOf(
            -R.attr.state_zero, -R.attr.state_one, R.attr.state_two
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("TAG",""+progress)
                when (progress) {
                    in 0..30 -> iv.setImageState(STATE_ZERO, true)
                    in 31..60 -> iv.setImageState(STATE_ONE, true)
                    in 61..100 -> iv.setImageState(STATE_TWO, true)
                }
            }
        })
    }
}

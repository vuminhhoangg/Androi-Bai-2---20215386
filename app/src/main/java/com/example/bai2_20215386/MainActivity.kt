package com.example.bai2_20215386

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var lvResult: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        etNumber = findViewById(R.id.et_number)
        rbEven = findViewById(R.id.rb_even)
        rbOdd = findViewById(R.id.rb_odd)
        rbSquare = findViewById(R.id.rb_square)
        btnShow = findViewById(R.id.btn_show)
        lvResult = findViewById(R.id.lv_result)
        tvError = findViewById(R.id.tv_error)

        // Thiết lập sự kiện nhấn nút Show
        btnShow.setOnClickListener {
            tvError.visibility = TextView.GONE // Ẩn thông báo lỗi
            val input = etNumber.text.toString()

            // Kiểm tra nếu người dùng không nhập gì hoặc nhập số không hợp lệ
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập một số nguyên dương!"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập một số nguyên dương hợp lệ!"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            // Dựa trên loại số đã chọn, lấy danh sách số phù hợp
            val numbersList = when {
                rbEven.isChecked -> getEvenNumbers(n)
                rbOdd.isChecked -> getOddNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                else -> {
                    tvError.text = "Vui lòng chọn một loại số!"
                    tvError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            // Hiển thị danh sách trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbersList)
            lvResult.adapter = adapter
        }
    }

    // Hàm lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Hàm lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Hàm lấy danh sách số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}

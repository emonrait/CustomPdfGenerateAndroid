package com.example.createpdfreport

import android.Manifest
import android.Manifest.permission
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import com.example.createpdfreport.helpers.PermissionHelper
import com.example.createpdfreport.model.Transaction
import com.rheyansh.lenden.model.PdfGeneratorModel
import com.rheyansh.rpdfgenerator.PdfGenerator


class MainActivity : AppCompatActivity() {
    private lateinit var dummyInfo: PdfGeneratorModel

    private lateinit var btnGenPDF: Button

    private val PERMISSION_REQUEST_CODE = 200
    private var isGenerating = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenPDF = findViewById(R.id.btnGenPDF)

        dummyInfo = dummyModel()

        createPdf(false) //just ask permission for first time


        btnGenPDF.setOnClickListener {
            createPdf(true)
        }


    }

    fun createPdf(download: Boolean) {

        val permissionHelper = PermissionHelper(
            this,
            arrayOf(
                permission.READ_EXTERNAL_STORAGE,
                permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )
        permissionHelper.denied {
            if (it) {
                Log.d("Permission check", "Permission denied by system")
                permissionHelper.openAppDetailsActivity()
            } else {
                Log.d("Permission check", "Permission denied")
            }
        }

//Request all permission
        permissionHelper.requestAll {
            Log.d("Permission check", "All permission granted")

            if (!isGenerating && download) {
                isGenerating = true
                PdfGenerator.generatePdf(this, dummyInfo)

                val handler = Handler()
                val runnable = Runnable {
                    //to avoid multiple generation at the same time. Set isGenerating = false on some delay
                    isGenerating = false
                }
                handler.postDelayed(runnable, 2000)
            }
        }

//Request individual permission
        permissionHelper.requestIndividual {
            Log.d("Permission check", "Individual Permission Granted")
        }
    }

    private fun dummyModel(): PdfGeneratorModel {
        val list = dummyTransactions()
        val header = "Bank Asia Ltd"
        val daterange = "20/01/2021-20/09/2021"
        val dummy = PdfGeneratorModel(list, header, daterange)
        return dummy
    }

    private fun dummyTransactions(): List<Transaction> {

        val list = arrayListOf<Transaction>()

        val i1 = Transaction()
        i1.date = "01/01/2021"
        i1.debitAmount = 0.0
        i1.creditAmount = 120.0
        i1.balance = 40.0
        i1.particulars = "Fund Transfer through by Smart App 01/02/2021 To City Bank"
        list.add(i1)


        val i2 = Transaction()
        i2.date = "02/01/2021"
        i2.debitAmount = 200.0
        i2.creditAmount = 0.0
        i2.balance = 40.0
        i2.particulars = "Fund Transfer through by Smart App 01/02/2021 To City Bank"
        list.add(i2)

        val i3 = Transaction()
        i3.date = "03/01/2021"
        i3.debitAmount = 100.0
        i3.creditAmount = 0.0
        i3.balance = 40.0
        i3.particulars = "Fund Transfer through by Smart App 01/02/2021 To City Bank"
        list.add(i3)

        val i4 = Transaction()
        i4.date = "04/01/2021"
        i4.debitAmount = 0.0
        i4.creditAmount = 180.0
        i4.balance = 40.0
        i4.particulars = "Fund Transfer through by Smart App 01/02/2021 To City Bank"
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)
        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)

        list.add(i1)
        list.add(i2)
        list.add(i3)
        list.add(i4)



        return list
    }


}
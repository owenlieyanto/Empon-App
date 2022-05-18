package com.example.empon_app.ui.detect

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.volley.Request.Method.POST
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.empon_app.FileDataPart
import com.example.empon_app.MainActivity.Companion.empons
import com.example.empon_app.R
import com.example.empon_app.VolleyFileUploadRequest
import com.example.empon_app.databinding.FragmentDetectBinding
import kotlinx.android.synthetic.main.fragment_detect.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import kotlin.collections.set


class DetectFragment : Fragment() {

    private var _binding: FragmentDetectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val pickImage = 100
    private var imageUri: Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonProcess.visibility = View.INVISIBLE

        buttonProcess.setOnClickListener { itView: View? ->
            val bitmap = (imageViewDetect.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageToBeUploaded: ByteArray = baos.toByteArray()

            // TODO: use real API from internet
//            val url = "http://owenlie.pythonanywhere.com/predict"
            val url = "http://10.0.2.2:5000/predict"
            val request = object : VolleyFileUploadRequest(
                POST,
                url,
                Response.Listener {
                    val json = String(it!!.data)
                    val jsonObject = JSONObject(json)
                    val predicted_empon = jsonObject.get("predicted_class")

                    // TODO: get real accuracy
                    val accuracy = jsonObject.get("accuracy")
                    val idEmpon =
                        empons.single { empon -> empon.kodeJenis == predicted_empon }.id

                    Log.d("asdf", "response is: $json")

                    val action = DetectFragmentDirections.actionToCaptureResultFragment(
                        idEmpon!!, accuracy.toString().toFloat()
                    )
                    Navigation.findNavController(itView!!).navigate(action)
                },
                Response.ErrorListener {
                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    Log.d("asdf", "error is: $it")
                }
            ) {
                override fun getByteData(): MutableMap<String, FileDataPart> {
                    val params = HashMap<String, FileDataPart>()
                    params["file"] = FileDataPart("image.jpeg", imageToBeUploaded, "jpeg")
                    return params
                }
            }
            Volley.newRequestQueue(context).add(request)

        }

        buttonUpload.setOnClickListener {
            val opt = arrayOf("Camera", "Gallery")
            val builder = AlertDialog.Builder(context)
            with(builder)
            {
                setTitle("Choose an option")
                setItems(opt) { _, which ->
                    if (opt[which] == "Camera") {
                        val gallery = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        try {
                            startActivityForResult(gallery, REQUEST_IMAGE_CAPTURE)
                        } catch (e: ActivityNotFoundException) {
                            // display error state to the user
                        }
                    } else {
                        val gallery =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                        startActivityForResult(gallery, pickImage)
                    }
                }
                show()
            }
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageViewDetect.setImageURI(imageUri)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageViewDetect.setImageBitmap(imageBitmap)
        }
    }

    override fun onResume() {
        super.onResume()

        if (imageViewDetect.drawable == null) {
            buttonProcess.visibility = View.INVISIBLE
        } else {
            buttonProcess.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
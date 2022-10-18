package com.kongsub.mygallery

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load

// 컴파일시 결정되는 상수. 파일내에서 어디든 사용 가능.
private const val ARG_URI = "uri"
class PhotoFragment : Fragment() {
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ARG_URI 키에 저장된 uri 값 얻기.
        arguments?.getParcelable<Uri>(ARG_URI)?.let {
            uri = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 프래그먼트에 표시할 뷰를 생성
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        //requireContext() : 프래그먼트에서 컨텍스트 얻기.
        val descriptor = requireContext().contentResolver.openFileDescriptor(uri, "r")
        descriptor?.use {
            val bitmap = BitmapFactory.decodeFileDescriptor(descriptor.fileDescriptor)
            imageView.load(bitmap)
        }
    }

    // newInstance() 메서드 : 프래그먼트를 생성함.
    // 인자 값으로 Uri 를 전달함. 전달한 Uri 는 Bundle 객체에 ARG_URL 키로 저장
    companion object {
        @JvmStatic
        fun newInstance(uri: Uri) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_URI, uri)

                }
            }
    }
}
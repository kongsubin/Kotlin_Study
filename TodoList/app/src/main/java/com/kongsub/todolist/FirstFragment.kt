package com.kongsub.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kongsub.todolist.adapter.TodoListAdapter
import com.kongsub.todolist.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    // view 모델 선언
    // activityViewModels : 뷰 모델 인스턴스를 얻을 수 있음.
    private val viewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val todoListAdapter = TodoListAdapter { todo ->
            viewModel.selectedTodo = todo
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = todoListAdapter

        // lifecycleScope : 지연함수를 사용할 수 있는 코루틴 스코프를 제공
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // collect 메서드 : suspense 함수로 지연실행됨.
                viewModel.items.collect{
                    Log.d("FirstFragment", it.toString())
                    todoListAdapter.submitList(it)
                }
            }
        }

        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
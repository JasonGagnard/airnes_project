package fr.example.airnes_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import fr.example.airnes_project.R;
import fr.example.airnes_project.adapters.PopularAdapters;
import fr.example.airnes_project.databinding.FragmentHomeBinding;
import fr.example.airnes_project.models.PopularModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    List<PopularModel> PopularModelList;
    PopularAdapters popularAdapters;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);
    return root;
    }
}
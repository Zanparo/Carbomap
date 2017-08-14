package com.epitech.carbomap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.epitech.carbomap.model.ScenarResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.NORMAL;
import static android.view.View.GONE;
import static android.view.View.SCALE_X;

/**
 * Created by Darracq Samuel on 18/04/2017.
 */

/** Activity used for all user interactions destined to set values of internal datas.
 * This activity is linked to simulation_layout
 */
public class SimulationActivity extends AppCompatActivity {

    /** ScenarResult is the class used to store datas
     *  In this case, _result will store all datas entered by the user.
     */
    private ScenarResult _result = new ScenarResult();

    /**
     * onCreate is called when the SimulationActivity is created.
     * This function is used to setup design elemets who needs it.
     * setContentView is called to set scenar_layout as the activity layout.
     * See @setSpinners() for more.
     * @param savedInstanceState basic Bundle instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenar_layout);
        setSpinners();
    }

    /**
     * Setup spinners contained in scenar_layout
     * Set priority and flexibility buttons too.
     */
    public void setSpinners() {
        ArrayList<Button> priority = new ArrayList<Button>();
        ArrayList<Button> flexibility = new ArrayList<Button>();

        //Two containers used to conatins all <priority> and <flexibility> buttons and to setup them as fast as possible
        priority.addAll(Arrays.asList(((Button) findViewById(R.id.budget_priority_button)), ((Button) findViewById(R.id.area_priority_button)), ((Button) findViewById(R.id.spatial_priority_button)), ((Button) findViewById(R.id.need_data_priority_button)), ((Button) findViewById(R.id.past_data_priority_button)), ((Button) findViewById(R.id.quick_access_priority_button)), ((Button) findViewById(R.id.frequency_priority_button))));
        flexibility.addAll(Arrays.asList(((Button) findViewById(R.id.budget_flexibility_button)), ((Button) findViewById(R.id.area_flexibility_button)), ((Button) findViewById(R.id.spatial_flexibility_button)), ((Button) findViewById(R.id.need_data_flexibility_button)), ((Button) findViewById(R.id.past_data_flexibility_button)), ((Button) findViewById(R.id.quick_access_flexibility_button)), ((Button) findViewById(R.id.frequency_flexibility_button))));

        for (Button b : priority) {
            b.setText(getResources().getStringArray(R.array.priority_a)[1]);
        }
        for (Button b : flexibility) {
            b.setText(getResources().getStringArray(R.array.flexibility_a)[1]);
        }

        // basic void title() function to setup a specific spinner or switch
        setScenarSpinner();
        setBudgetUnitSwitch();
        setAreaDistribSpinner();
        setSpatialSpinner();
        setAreaUnitSwitch();
        setPastDataSpinner();
        setNeedDataSpinner();
        setQuickAccessSpinner();
        setFrequencySpinner();
        setTotalAreaUnitSwitch();
    }

    /** This function is simply used for user experience.
     * When a question category main title is clicked, the set of question appears and the selected
     * element is colored etc.
     * @param v View of the TextView selected by the user
     */
    public void changeQuestionsFocus(View v)
    {
        LinearLayout budget_layout = (LinearLayout) findViewById(R.id.budget_questions_layout);
        LinearLayout area_layout = (LinearLayout) findViewById(R.id.area_questions_layout);
        LinearLayout key_layout = (LinearLayout) findViewById(R.id.key_questions_layout);
        TextView budget_text = (TextView) findViewById(R.id.budget_questions_appear);
        TextView area_text = (TextView) findViewById(R.id.area_questions_appear);
        TextView key_text = (TextView) findViewById(R.id.key_questions_appear);
        String str = v.getResources().getResourceName(v.getId()).split("id/")[1];

        v.setBackground((v.getBackground() == getResources().getDrawable(R.drawable.borders) ? getResources().getDrawable(R.drawable.borders_light_blue) : getResources().getDrawable(R.drawable.borders)));
        switch (str) {
            case "budget_questions_appear" :
                budget_text.setTypeface(null, (budget_layout.getVisibility() == GONE) ? BOLD : NORMAL);
                budget_text.setBackground((budget_layout.getVisibility() == View.GONE) ? getResources().getDrawable(R.drawable.borders_light_blue) : getResources().getDrawable(R.drawable.borders));
                area_text.setBackground(getResources().getDrawable(R.drawable.borders));
                key_text.setBackground(getResources().getDrawable(R.drawable.borders));
                area_text.setTypeface(null, NORMAL);
                key_text.setTypeface(null, NORMAL);
                budget_layout.setVisibility((budget_layout.getVisibility() == GONE) ? View.VISIBLE : GONE);
                area_layout.setVisibility(GONE);
                key_layout.setVisibility(GONE);
                break;
            case "area_questions_appear" :
                area_text.setTypeface(null, (area_layout.getVisibility() == GONE) ? BOLD : NORMAL);
                area_text.setBackground((area_layout.getVisibility() == View.GONE) ? getResources().getDrawable(R.drawable.borders_light_blue) : getResources().getDrawable(R.drawable.borders));
                budget_text.setBackground(getResources().getDrawable(R.drawable.borders));
                key_text.setBackground(getResources().getDrawable(R.drawable.borders));
                budget_text.setTypeface(null, NORMAL);
                key_text.setTypeface(null, NORMAL);
                area_layout.setVisibility((area_layout.getVisibility() == GONE) ? View.VISIBLE : GONE);
                budget_layout.setVisibility(GONE);
                key_layout.setVisibility(GONE);
                break;
            case "key_questions_appear" :
                key_text.setTypeface(null, (key_layout.getVisibility() == GONE) ? BOLD : NORMAL);
                key_text.setBackground((key_layout.getVisibility() == View.GONE) ? getResources().getDrawable(R.drawable.borders_light_blue) : getResources().getDrawable(R.drawable.borders));
                area_text.setBackground(getResources().getDrawable(R.drawable.borders));
                budget_text.setBackground(getResources().getDrawable(R.drawable.borders));
                area_text.setTypeface(null, NORMAL);
                budget_text.setTypeface(null, NORMAL);
                key_layout.setVisibility((key_layout.getVisibility() == GONE) ? View.VISIBLE : GONE);
                area_layout.setVisibility(GONE);
                budget_layout.setVisibility(GONE);
                break;
            default :
                return;
        }
    }

    /**
     * findSetter is used for all "priority" and "flexibility" buttons. The buttons name is used to recognize
     * the value associeted to the priority or flexibility modified.
     * @param buttonName name of the button clicked by the user
     */
    public void findSetter(String buttonName)
    {
        String cutButtonName = buttonName.split("/")[1];
        switch (cutButtonName){
            case "spatial_flexibility_button" :
                _result.setSpatialFlexibility(_result.getSpatialFlexibility().ordinal() + 1);
                break;
            case "spatial_priority_button" :
                _result.setSpatialPriority(_result.getSpatialPriority().ordinal() +1);
                break;
            case "area_priority_button" :
                _result.setAreaPriority(_result.getAreaPriority().ordinal() + 1);
                break;
            case "area_flexibility_button" :
                _result.setAreaFlexibility(_result.getAreaFlexibility().ordinal() + 1);
                break;
            case "budget_priority_button" :
                _result.setBudgetPriority(_result.getBudgetPriority().ordinal() + 1);
                break;
            case "budget_flexibility_button" :
                _result.setBudgetFlexibility(_result.getBudgetFlexibility().ordinal() + 1);
                break;
            case "need_data_priority_button" :
                _result.setNeedDataPriority(_result.getNeedDataPriority().ordinal() + 1);
                break;
            case "need_data_flexibility_button" :
                _result.setNeedDataFlexibility(_result.getNeedDataFlexibility().ordinal() + 1);
                break;
            case "past_data_priority_button" :
                _result.setPastDataPriority(_result.getPastDataPriority().ordinal() + 1);
                break;
            case "past_data_flexibility_button" :
                _result.setPastDataFlexibility(_result.getPastDataFlexibility().ordinal() + 1);
                break;
            case "quick_access_priority_button" :
                _result.setQuickAccessPriority(_result.getQuickAccessPriority().ordinal() + 1);
                break;
            case "quick_access_flexibility_button" :
                _result.setQuickAccessFlexiblity(_result.getQuickAccessFlexibility().ordinal() + 1);
                break;
            case "frequency_priority_button" :
                _result.setFrequencyPriority(_result.getFrequencyPriority().ordinal() + 1);
                break;
            case "frequency_flexibility_button" :
                _result.setFrequencyFlexibility(_result.getFrequencyFlexibility().ordinal() + 1);
                break;
        }
    }

    /** This function is called when a "priority" button is pressed.
     * It automtically changes the text on the button and the value corresponding to this button
     * @param v View associated to the button pressed
     */
    public void changePriorityText(View v) {
        Button b = (Button) findViewById(v.getId());
        int i = 0;
        for (; !getResources().getStringArray(R.array.priority_a)[i].equals(b.getText()); i++) ;
        i++;
        if (i == getResources().getStringArray(R.array.priority_a).length)
            i = 1;
        b.setText(getResources().getStringArray(R.array.priority_a)[i]);
        findSetter(v.getResources().getResourceName(v.getId()));
    }

    /** This function is called when a "flexibility" button is pressed.
     * It automtically changes the text on the button and the value corresponding to this button
     * @param v View associated to the button pressed
     */
    public void changeFlexiblityText(View v) {
        Button b = (Button) findViewById(v.getId());
        int i = 0;
        for (; !getResources().getStringArray(R.array.flexibility_a)[i].equals(b.getText()); i++) ;
        i++;
        if (i == getResources().getStringArray(R.array.flexibility_a).length)
            i = 1;
        b.setText(getResources().getStringArray(R.array.flexibility_a)[i]);
        findSetter(v.getResources().getResourceName(v.getId()));
    }

    /**
     * SwitchBudgetQuestion is called when the budget_question is clicked
     * Only used to change value of text displayed and set the correct value into _result
     * @param v View of the button clicked
     */
    public void switchBudgetQuestion(View v) {
        Button button = (Button) findViewById(R.id.budget_q_button);
        LinearLayout layout = (LinearLayout) findViewById(R.id.value_budget_layout);
        if (button.getText().equals(getString(R.string.budget_a))) {
            button.setText(getString(R.string.budget_a1));
            layout.setVisibility(View.VISIBLE);
        } else {
            button.setText(getString(R.string.budget_a));
            layout.setVisibility(GONE);
        }
    }

    /**
     * This function is used to set the spinner about scenari choices
     * In this function is set the spinner and the listener on it
     * The listener is used to change values concerned in _result and to
     * make categories of questions appear
     */
    public void setScenarSpinner() {
        Spinner scenar_spinner = (Spinner) findViewById(R.id.scenar_spinner);

        //Listener
        scenar_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            LinearLayout general_layout = (LinearLayout) findViewById(R.id.general_layout);
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView budget_appear = (TextView) findViewById(R.id.budget_questions_appear);
                TextView area_appear = (TextView) findViewById(R.id.area_questions_appear);
                TextView key_appear = (TextView) findViewById(R.id.key_questions_appear);
                //Set scenar in _result
                _result.setScenar(i);
                if (i == 0) {
                    general_layout.setVisibility(GONE);
                    budget_appear.setVisibility(GONE);
                    area_appear.setVisibility(GONE);
                    key_appear.setVisibility(GONE);
                }
                //If the scenar is set, questions categories must appear
                else {
                    general_layout.setVisibility(View.VISIBLE);
                    budget_appear.setVisibility(View.VISIBLE);
                    area_appear.setVisibility(View.VISIBLE);
                    key_appear.setVisibility(View.VISIBLE);
                 }
            }

            //For safety of the app, this function can be completed
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.scenars, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        scenar_spinner.setAdapter(adapter);
    }

    /**
     * setSpatialSpinner is used to set the spinner about the spatial resolution needed
     * Only used to set the value into _result
     */
    public void setSpatialSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spatial_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setSpatial(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * setPastDataSpinner is used to set the spinner about how old past datas the user needs
     * Only used to set the value into _result
     */
    public void setPastDataSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.past_data_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setPastData(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * setNeedDataSpinner is used to set the spinner about how soon the user needs datas
     * Only used to set the value into _result
     */
    public void setNeedDataSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.need_data_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setNeedData(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * setQuickAccessSpinner is used to set the spinner about how quickly the user needs to access datas
     * Only used to set the value into _result
     */
    public void setQuickAccessSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.quick_access_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setQuickAccess(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * setFrequencySpinner is used to set the spinner about the frequency needed
     * Only used to set the value into _result
     */
    public void setFrequencySpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.frequency_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setFrequency(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * setAreaDistribSpinner is used to set the spinner about the area of the distribution needed.
     * This function is used to set the value into _result and to make the total_areas_layout and
     * area_all_layout appear depending on AreaDistrib value selected
     */
    public void setAreaDistribSpinner() {
        final LinearLayout total_areas_layout = (LinearLayout) findViewById(R.id.total_areas_layout);
        final LinearLayout global_layout = (LinearLayout) findViewById(R.id.area_all_layout);
        Spinner area_distrib_spinner = (Spinner) findViewById(R.id.area_distrib_spinner);
        area_distrib_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setAreaDistrib(i);
                if (i != 0) {
                    global_layout.setVisibility(View.VISIBLE);
                    if (i != 1)
                        total_areas_layout.setVisibility(View.VISIBLE);
                    else
                        total_areas_layout.setVisibility(GONE);
                } else
                    total_areas_layout.setVisibility(GONE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                global_layout.setVisibility(GONE);
            }
        });
    }

    /**
     * setAreaUnitSwitch is used to initialize the switch about the area unit
     * Only used to set the correct value of _areaUnit into _result
     */
    public void setAreaUnitSwitch() {
        Switch area_unit_switch = (Switch) findViewById(R.id.area_unit_switch);
        area_unit_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    _result.setAreaUnit(1);
                }
                else
                    _result.setAreaUnit(0);
            }
        });
    }

    /**
     * setBudgetUnitSwitch is used to initialize the switch about the budget unit
     * Only used to set the correct value of _budgetUnit into _result
     */
    public void setBudgetUnitSwitch() {
        Switch budget_unit_switch = (Switch) findViewById(R.id.budget_unit_switch);
        budget_unit_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    _result.setBudgetUnit(1);
                else
                    _result.setBudgetUnit(0);
            }
        });
    }

    /** setTotalAreaUnitSwitch is used to set the switch about the total area unit
     *  Only used to set the correct value of _totalAreaUnit into _result
     */
    public void setTotalAreaUnitSwitch() {
        Switch area_unit_switch = (Switch) findViewById(R.id.total_area_unit_switch);
        area_unit_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    _result.setTotalAreasUnit(1);
                }
                else
                    _result.setTotalAreasUnit(0);
            }
        });
        /*Spinner area_unit_spinner = (Spinner) findViewById(R.id.area_unit_spinner);
        area_unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _result.setAreaUnit(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

    /**
     * generateResults has been created only for debug.
     * It is associated to the textview exposal_test and, if all necessaries values have been completed,
     * displays the latter. If not, it displays "fields missing"
     * @param v View of the button
     */
    public void generateResults(View v) {
        TextView tv = (TextView) findViewById(R.id.exposal_text);
        try {
            _result.setBudget(Double.parseDouble(((EditText) findViewById(R.id.edit_budget_unit)).getText().toString()));
        } catch (Exception e)
        {
            _result.setBudget(-1);
        }
        try {
            _result.setAreaAmount(Integer.parseInt(((EditText) findViewById(R.id.edit_area_amount)).getText().toString()));
        } catch (Exception e)
        {
            _result.setAreaAmount(-1);
        }
        try {
            _result.setTotalAreas(Integer.parseInt(((EditText) findViewById(R.id.edit_total_areas)).getText().toString()));
        } catch (Exception e)
        {
            _result.setTotalAreas(-1);
        }
        tv.setText(_result.expose());
    }


}

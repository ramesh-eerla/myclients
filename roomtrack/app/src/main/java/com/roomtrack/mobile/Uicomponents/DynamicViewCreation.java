package com.roomtrack.mobile.Uicomponents;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Ramesh.eerla on 24/10/2018.
 */

public class DynamicViewCreation {

    // Types of input texts

    public static int CONTROL_ITEM;
    public static String CONTROL_TYPE = "controlType";
    public static final int TYPEAHEAD = 551;
    public static final int DATEPICKER = 552;
    public static final int DUAL_TEXTBOX = 553;
    public static final int TEXTBOX = 554;
    public static final int SPINNER_VIEW = 555;
    public static int DUALTEXTBOX_TAG = 900;
    private static Context nContext = null;
    public static HashMap<Integer, AutoCompleteTextView> autoCompleteTextViewHashMap = new HashMap<>();
    public static HashMap<Integer, String> autoCompleteTextvalue = new HashMap<>();
    public static HashMap<Integer, Button> buttonHashMap = new HashMap<>();
    public static HashMap<Integer, CustomEditText> editetextboxHashmap = new HashMap<>();
    public static HashMap<String, LinearLayout> dualtextboxHashmap = new HashMap<>();
    public static DateTimeModel showDateModel;
    public static HashMap<Integer, AutoCompleteTextView> buttonAutoCompleteTextView = new HashMap<>();
    public static HashMap<String, String> finalMaptoSubmit = new HashMap<>();

    public static ArrayList<CreateRquirementDynamicViewObjects> dynamicViewObjectsArrayList = new ArrayList<>();

    public static Calendar calendar;
    public static int year, month, day;
    //    public static AutoCompleteTextView datePickAutoCompleteTextView;
    public static TextInputLayout textInputLayout;
    public static Button submit, cancel, months_3, months_6, months_9, months_12;
    public final static Calendar date = Calendar.getInstance();
    public static int endDateAutoCompleteId = 0;
    public static SimpleDateFormat dateFormatter;
    public static CustomEditText billrate_et1, billrate_et2, contractValue_ET, weekly_spend;
    public static CreateRequirementPageNew createRequirementPage;
    public static boolean shouldAutoComplete = true;
    public static ArrayAdapter<String> adapter;
    public static boolean viewCreated = false;
    public static Date defaultDate;
    static Boolean endDateClicked = false;
    final static Handler handler = new Handler();

    public DynamicViewCreation() {
        /* ---------------Clearing the array values--------------*/

        autoCompleteTextViewHashMap.clear();
        autoCompleteTextViewHashMap = new HashMap<>();
        buttonAutoCompleteTextView.clear();
        buttonAutoCompleteTextView = new HashMap<>();
        autoCompleteTextvalue.clear();
        autoCompleteTextvalue=new HashMap<>();
        editetextboxHashmap.clear();
        editetextboxHashmap = new HashMap<>();
        dualtextboxHashmap = new HashMap<>();
        dynamicViewObjectsArrayList.clear();
        dynamicViewObjectsArrayList = new ArrayList<>();


    }

    // Main method to create dynamic views
    public static ViewGroup createDynamicViews(List<UiViewModel> uiViewModelList, ViewGroup viewGroup, Activity activity) {
        if (uiViewModelList != null) {
            for (int i = 0; i < uiViewModelList.size(); i++) {
                UiViewModel uiViewModel = uiViewModelList.get(i);
                if (uiViewModel != null) {
                    View view = null, view1 = null;
                    if ((uiViewModel.inputType != null &&
                            (uiViewModel.inputType.equalsIgnoreCase("Label") ||
                                    uiViewModel.inputType.equalsIgnoreCase("TextView")) && uiViewModel.viewDisplay)) {
                        if (uiViewModel.key != null && !uiViewModel.key.equalsIgnoreCase("field")) {
                            if (uiViewModel.key.equalsIgnoreCase("RejectType")) {
                                view = getLeftAlignedSingleLabel(activity, uiViewModel);
                                view1 = getDynamicRadioWithExtraView(activity, uiViewModel);
                            } else {
                                view = getLeftAlignedTitleAndDescription(activity, uiViewModel);
                            }
                        }
                    } else if ((uiViewModel.inputType != null &&
                            (uiViewModel.inputType.equalsIgnoreCase("dropdown") || uiViewModel.inputType.equalsIgnoreCase("Select")) &&
                            uiViewModel.viewDisplay)) {
                        view = getDynamicSpinner(activity, uiViewModel, viewGroup);
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.equalsIgnoreCase("typeahead") && uiViewModel.viewDisplay)) {
                        if (uiViewModel.name != null && (uiViewModel.name.equalsIgnoreCase("state")
                                || uiViewModel.name.equalsIgnoreCase("country")
                                || uiViewModel.name.equalsIgnoreCase("county"))) {
                            view = getDynamicSpinner(activity, uiViewModel, viewGroup);
                        }
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.equalsIgnoreCase("Radio") && uiViewModel.viewDisplay)) {
                        view = getLeftAlignedSingleLabel(activity, uiViewModel);
                        view1 = getDynamicRadioWithHorizontalView(activity, uiViewModel, viewGroup);
                    } else if ((uiViewModel.inputType != null && (uiViewModel.inputType.equalsIgnoreCase("TextBox") ||
                            uiViewModel.inputType.equalsIgnoreCase("text")) && uiViewModel.viewDisplay)) {
                        if (!uiViewModel.key.equalsIgnoreCase("STID") || !uiViewModel.isHasInfo) {
                            if (uiViewModel.key.equalsIgnoreCase("ChargeNo")) {
                                view = getDynamicAutoCompleteText(activity, uiViewModel);
                            } else
                                view = getLabelOrLabelWithEditText(activity, uiViewModel, viewGroup);
                        } else {
                            view = getLeftAlignedNormalLabel(activity, uiViewModel, "black");
                        }
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.equalsIgnoreCase("datepicker") && uiViewModel.viewDisplay)) {
                        view = getDatePickerView(activity, uiViewModel, viewGroup);
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.equalsIgnoreCase("title") && uiViewModel.viewDisplay)) {
                        if (uiViewModel.key.equalsIgnoreCase("ReasonId")) {
                            view = getLeftAlignedSingleLabel(activity, uiViewModel);
                            view1 = getDynamicRadio(activity, uiViewModel, viewGroup);
                        } else {
                            view = getLeftAlignedNormalLabel(activity, uiViewModel, "black");
                        }
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.equalsIgnoreCase("List") && uiViewModel.viewDisplay)) {
                        view = getLeftAlignedTitleAndDescription(activity, uiViewModel);
                    } else if ((uiViewModel.inputType != null && uiViewModel.inputType.matches(""))) {
                        if (uiViewModel.name != null && uiViewModel.name.equalsIgnoreCase("lbSaleTaxLocPreferredTitle"))
                            view = getLeftAlignedNormalLabel(activity, uiViewModel, "black");
                    } else if (uiViewModel.inputType == null) {
                        if (uiViewModel.key.equalsIgnoreCase("LblUsername") && uiViewModel.viewDisplay) {
                            uiViewModel.required = true;
                            view = getLabelOrLabelWithEditText(activity, uiViewModel, viewGroup);
                        } else if (uiViewModel.key.equalsIgnoreCase("redOnlyLabel") && uiViewModel.viewDisplay)
                            view = getLeftAlignedNormalLabel(activity, uiViewModel, "redOnlyLabel");
                        else if (uiViewModel.key.equalsIgnoreCase("centerHeading") && uiViewModel.viewDisplay)
                            view = getCenterAlignedHeaderLabel(activity, uiViewModel.name);
                        else if (uiViewModel.viewDisplay)
                            view = getLeftAlignedNormalLabel(activity, uiViewModel, "");
                    }

                    if (view != null) {
                        viewGroup.addView(view);
                    }

                    if (view1 != null) {
                        viewGroup.addView(view1);
                    }
                }
            }
        }
        return viewGroup;
    }

    public static View getDynamicAutoCompleteText(final Context context, final UiViewModel uiViewModel) {
        View view;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.view_datepicker, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.titleDatePicker);
        view.findViewById(R.id.rlFieldStartDate_test).setVisibility(View.GONE);
        view.findViewById(R.id.btnDatePicker).setVisibility(View.GONE);
        tvTitle.setPadding(5, 15, 0, 5);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.fieldStartTextView);
        autoCompleteTextView.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        autoCompleteTextView.setPadding(10, -10, 0, 0);
        final AutocompleteCustomArrayAdapter[] myAdapter = {null};
        final Call<List<KeyValueModel>>[] finalMServiceGlobal = null;
        final ArrayList<Object> filteredList = new ArrayList<>();
        if (uiViewModel != null) {
            setTextToDynamicLabel(tvTitle, uiViewModel);
            if (uiViewModel.value != null) {
                autoCompleteTextView.setText(uiViewModel.value);
            }
            if (uiViewModel.TypeAheadFunctionName != null && !uiViewModel.TypeAheadFunctionName.matches(""))
                autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        boolean userChange = Math.abs(i2 - i1) == 1;
                        if (userChange) {
                            if (autoCompleteTextView.isPerformingCompletion()) {
                                // An item has been selected from the list. Ignore.
                                return;
                            }
                            if (autoCompleteTextView.getText().toString().trim().length() > 0) {
                            /*if (finalMServiceGlobal != null && finalMServiceGlobal[0] != null)
                                finalMServiceGlobal[0].cancel();*/
                                autoCompleteTextView.setTag(R.id.TagId, null);
                                BasicRequestParams requestParams = new BasicRequestParams();
                                requestParams.keywords = autoCompleteTextView.getText().toString();
                                /*finalMServiceGlobal[0] = */
                                RetrofitUtil.callKeyValueModelList((Activity) context,
                                        requestParams, uiViewModel.TypeAheadFunctionName, context.getResources().getString(R.string.webapi_urn_5),
                                        new RetrofitListInterface() {
                                            @Override
                                            public void onResponseList(Call<List<KeyValueModel>> call, Response<List<KeyValueModel>> response, Call<List<KeyValueModel>> mService) {
                                                filteredList.clear();
                                                filteredList.addAll(response.body());

                                                myAdapter[0] = new AutocompleteCustomArrayAdapter(context, R.layout.spinner_custom, filteredList);

                                                if (filteredList != null && filteredList.size() > 0) {
                                                    autoCompleteTextView.setThreshold(1);
                                                    autoCompleteTextView.setAdapter(myAdapter[0]);
                                                    autoCompleteTextView.showDropDown();
                                                    myAdapter[0].notifyDataSetChanged();

                                                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                            TextView tv = (TextView) view;
                                                            KeyValueModel myObject = (KeyValueModel) tv.getTag();
                                                            autoCompleteTextView.setText(myObject.getName());
                                                            autoCompleteTextView.setSelection(myObject.getName().length());
                                                            autoCompleteTextView.setTag(R.id.TagId, myObject);
                                                            autoCompleteTextView.setAdapter(null);

                                                        }
                                                    });
                                                } else {
                                                    CommonHelper.getAlertDialogIncludesInterfaceListenerToCaller(
                                                            (Activity) context,
                                                            Constants.ST_INVALIDRESPONCE_Error_Message,
                                                            "",
                                                            false,
                                                            "",
                                                            "", 0, null);
                                                    autoCompleteTextView.setText("");
                                                }
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
        }
        autoCompleteTextView.setTag(uiViewModel);
        return view;
    }

    public static void setTextToDynamicLabel(TextView tvTitle, UiViewModel uiViewModel) {
        if (tvTitle != null && uiViewModel.name != null) {
            String name = uiViewModel.name.replace(":", " ");
            if (name.trim().length() > 0) {
                if (uiViewModel.required && !name.contains("*")) {
                    tvTitle.setText(ApprovalFontCommons.maditoryStringlable((uiViewModel.isKeyNameChanged) ? uiViewModel.changedName : name));
                } else {
                    tvTitle.setText((!uiViewModel.name.matches("")) ? (stringContainsHTML((uiViewModel.isKeyNameChanged) ? uiViewModel.changedName : name) ? Html.fromHtml((uiViewModel.isKeyNameChanged) ? uiViewModel.changedName : name) : (uiViewModel.isKeyNameChanged) ? uiViewModel.changedName : name) : "");
                }
            }
        }
    }

    //Get Left aligned Textview Title and Description
    public static View getDatePickerView(final Context context, final UiViewModel uiViewModel, ViewGroup viewGroup) {
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.view_datepicker, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.titleDatePicker);
        view.findViewById(R.id.rlFieldStartDate_test).setVisibility(View.GONE);
        tvTitle.setPadding(5, 5, 0, 5);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        final EditText tvDescription = (AutoCompleteTextView) view.findViewById(R.id.fieldStartTextView);
        tvDescription.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        tvDescription.setPadding(5, -10, 0, 0);
        //tvDescription.setEnabled(false);
        tvDescription.setFocusable(false);
        tvDescription.setFocusableInTouchMode(false);
        tvDescription.setTextColor(Color.BLACK);
        Button btnPickCalender = (Button) view.findViewById(R.id.btnDatePicker);
        view.findViewById(R.id.rlFieldStartDate_test).setVisibility(View.GONE);

        if (uiViewModel != null) {
            setTextToDynamicLabel(tvTitle, uiViewModel);
            if (uiViewModel.value != null) {
                tvDescription.setText(uiViewModel.value);
            }

            View.OnClickListener daOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final DateTimeModel dateTimeModel = DateTimePickerUtil.splitStringIntoDateModel(tvDescription.getText().toString());
                    if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("Duration")) {
                        DateTimePickerUtil.pickCalenderWithFixedMinAndMaxDate((Activity) context,
                                dateTimeModel,
                                new GetDateModelListener() {
                                    @Override
                                    public void getDateModel(DateTimeModel dateModel) {
                                        tvDescription.setText(DateTimePickerUtil.formatToStringFromDateModel(dateModel));
                                    }
                                }, false, false,
                                DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractFromDate),
                                DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractToDate));
                    } else if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("to")) {
                        if (uiViewModel.ExpenseFutureDateAllowed)
                            DateTimePickerUtil.pickCalenderWithFixedMinAndMaxDate((Activity) context,
                                    dateTimeModel,
                                    new GetDateModelListener() {
                                        @Override
                                        public void getDateModel(DateTimeModel dateModel) {
                                            tvDescription.setText(DateTimePickerUtil.formatToStringFromDateModel(dateModel));
                                        }
                                    }, false, false,
                                    DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractFromDate),
                                    DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractToDate));
                        else
                            DateTimePickerUtil.pickCalenderWithFixedMinAndMaxDate((Activity) context,
                                    dateTimeModel,
                                    new GetDateModelListener() {
                                        @Override
                                        public void getDateModel(DateTimeModel dateModel) {
                                            tvDescription.setText(DateTimePickerUtil.formatToStringFromDateModel(dateModel));
                                        }
                                    }, false, true,
                                    DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractFromDate),
                                    DateTimePickerUtil.splitStringIntoDateModel(uiViewModel.contractToDate));

                    } else {
                        DateTimePickerUtil.pickCalenderUponConditions((Activity) context,
                                dateTimeModel,
                                null,
                                null,
                                new GetDateModelListener() {
                                    @Override
                                    public void getDateModel(DateTimeModel dateModel) {
                                        tvDescription.setText(DateTimePickerUtil.formatToStringFromDateModel(dateModel));
                                    }
                                }, false, false, false);

                    }
                }
            };

            btnPickCalender.setOnClickListener(daOnClickListener);
            tvDescription.setOnClickListener(daOnClickListener);
        }

        if (uiViewModel.dependentViewKeysList != null && uiViewModel.dependentViewKeysList.size() > 0) {
            tvDescription.setTag(R.id.TagId, CommonUtils.getViewFromViewGroup(viewGroup,
                    uiViewModel.dependentViewKeysList.get(0).keyName,
                    uiViewModel.dependentViewKeysList.get(0).isKey));
        }

        if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("to")) {
            EditText editText = (EditText) CommonUtils.getViewFromViewGroup(viewGroup,
                    "Duration",
                    true);
            if (editText != null)
                editText.setTag(R.id.TagId, tvDescription);
        }
        tvDescription.setTag(uiViewModel);
        return view;
    }


    //Method to Create Spinner
    public static View getDynamicSpinner(final Context context, final UiViewModel uiViewModel, final ViewGroup viewGroup) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        View view = vi.inflate(R.layout.view_spinner, null);
        final Spinner spinner = (Spinner) view.findViewById(R.id.currency_spin);
        CustomTextView tvTitle = (CustomTextView) view.findViewById(R.id.currency_tv);
        tvTitle.setPadding(15, 10, 0, 0);
        tvTitle.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        spinner.setPadding(10, 0, 0, 0);
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.tab_default));
        int selectedPosition = -1;
        try {
            setTextToDynamicLabel(tvTitle, uiViewModel);
            final List<DropdownReasons> items = uiViewModel.dropdownReasons;
            if (items != null) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < items.size(); j++) {
                    list.add(items.get(j).getText() != null ? items.get(j).getText() : "");
                    if (items.get(j).Selected)
                        selectedPosition = j;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_custom, list) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(R.id.text_spinner);
                        text.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                spinner.setAdapter(adapter);
                if ((uiViewModel.isReadOnly != null) && uiViewModel.isReadOnly.equalsIgnoreCase("YES"))
                    spinner.setEnabled(false);
                spinner.setTag(uiViewModel);

                AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        List<DropdownReasons> addedItems = ((UiViewModel) spinner.getTag()).dropdownReasons;
                        DropdownReasons dropdownReasons = addedItems.get(i);
                        String paramValue = null;
                        if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("mm")) {
                            setAdapterToDDfromMM(context, viewGroup, dropdownReasons.Text);
                        } else if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("dd")) {
                            if (dropdownReasons.Text.contains("dd")) {
                                Spinner spinner1 = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "dd", true);
                                UiViewModel uiViewModel1 = (UiViewModel) spinner1.getTag();
                                uiViewModel1.value = dropdownReasons.Text;
                                spinner1.setTag(uiViewModel1);
                                return;
                            }

                        } else if (uiViewModel.key!=null&&uiViewModel.key.equalsIgnoreCase("altHiringManager")) {
                            finalMaptoSubmit.put(uiViewModel.key, dropdownReasons.Value);
                        } else {
                            String apiVersion = "", apiMethod = "";
                            BasicRequestParams basicRequestParams = new BasicRequestParams();
                            Spinner dependentSpinner = null;
                            String dependentValue = null;
                            if (uiViewModel.key == null && uiViewModel.name != null) {
                                paramValue = dropdownReasons.Value;
                                if (uiViewModel.name.equalsIgnoreCase("Country")) {
                                    basicRequestParams.Country = dropdownReasons.Value;
                                    apiVersion = context.getResources().getString(R.string.webapi_urn_5);
                                    apiMethod = context.getResources().getString(R.string.GettingStatesBasedonCountry);
                                    dependentSpinner = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "State", false);
                                    dependentValue = "state";
                                } else if (uiViewModel.name.equalsIgnoreCase("State")) {
                                    basicRequestParams.Code = dropdownReasons.Value;
                                    apiVersion = context.getResources().getString(R.string.webapi_urn_5);
                                    apiMethod = context.getResources().getString(R.string.GettingCountyBasedonState);
                                    dependentSpinner = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "County", false);
                                    dependentValue = "county";
                                } else if (uiViewModel.name.equalsIgnoreCase("county")) {
                                    Spinner stateSpinner = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "state", false);
                                    Spinner spinner1 = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "county", false);
                                    UiViewModel uiViewModel1 = (UiViewModel) spinner1.getTag();
                                    uiViewModel1.value = dropdownReasons.Text;
                                    uiViewModel.dropdownReasons1 = ((UiViewModel) stateSpinner.getTag()).dropdownReasons;
                                    spinner1.setTag(uiViewModel1);
                                    return;
                                }
                            }

                            if (paramValue != null && !paramValue.equalsIgnoreCase("") && !paramValue.equalsIgnoreCase("0")) {
                                final Spinner finalSpinner = dependentSpinner;
                                final String finalDependentValue = dependentValue;
                                RetrofitUtil.callDropDownReasonList((Activity) context, basicRequestParams, apiMethod, apiVersion, new RetrofitListInterface() {
                                    @Override
                                    public void onResponseDropDownReasonList(Response<List<DropdownReasons>> response, Call<List<DropdownReasons>> mService) {
                                        if (uiViewModel.key == null && uiViewModel.name != null) {
                                            List<DropdownReasons> dropdownReasonsArrayList = new ArrayList<>();
                                            dropdownReasonsArrayList.addAll(response.body());

                                            ArrayList<String> stringArrayList = CommonUtils.getTextsFromDropdownReasons(dropdownReasonsArrayList);
                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_custom, stringArrayList);
                                            try {
                                                finalSpinner.setAdapter(adapter);
                                                if (finalSpinner != null && finalSpinner.getTag() != null) {
                                                    UiViewModel uiViewModel1 = (UiViewModel) finalSpinner.getTag();
                                                    uiViewModel1.dropdownReasons = dropdownReasonsArrayList;
                                                    finalSpinner.setEnabled(true);
                                                    finalSpinner.setTag(uiViewModel1);
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            } else {
                                UiViewModel uiViewModel1;
                                uiViewModel1 = (UiViewModel) dependentSpinner.getTag();
                                uiViewModel1.dropdownReasons.clear();
                                uiViewModel1.dropdownReasons.add(new DropdownReasons(context.getResources().getString(R.string.select__), 0 + ""));
                                ArrayList<String> stringArrayList = CommonUtils.getTextsFromDropdownReasons(uiViewModel1.dropdownReasons);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_custom, stringArrayList);
                                dependentSpinner.setAdapter(adapter);
                                dependentSpinner.setTag(uiViewModel1);
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                };

                if (uiViewModel.noListener != 1) {
                    if (uiViewModel.key == null && uiViewModel.name != null &&
                            (uiViewModel.name.equalsIgnoreCase("country"))
                            || uiViewModel.name.equalsIgnoreCase("state")
                            || uiViewModel.name.equalsIgnoreCase("county")) {
                        spinner.setSelected(false);  // must
                        spinner.setSelection((selectedPosition != -1) ? selectedPosition : 0, true);  //must
                        spinner.setOnItemSelectedListener(spinnerListener);
                        selectedPosition = -1;
                    } else if (uiViewModel.key != null) {
                        if (uiViewModel.key.equalsIgnoreCase("mm")) {
                            spinner.setSelected(false);  // must
                            spinner.setSelection((uiViewModel.value != null && !uiViewModel.value.equalsIgnoreCase("") && !uiViewModel.value.contains("mm")) ? Integer.parseInt(uiViewModel.value) : 0, true);  //must
                            spinner.setOnItemSelectedListener(spinnerListener);
                        } else if (uiViewModel.key.equalsIgnoreCase("altHiringManager")) {
                            spinner.setOnItemSelectedListener(spinnerListener);

                        } else if (uiViewModel.key.equalsIgnoreCase("dd")) {
                            Spinner monthSpinner = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "mm", true);
                            UiViewModel dependentUiViewModel = (UiViewModel) monthSpinner.getTag();
                            try {
                                ArrayAdapter<String> dependentSpinnerAdapter = null;
                                List<DropdownReasons> dropdownReasons = new ArrayList<>();
                                List<String> daysString = new ArrayList<>();
                                if (isStringInteger(dependentUiViewModel.value)) {
                                    dropdownReasons = CommonUtils.getNoOfDaysFromPresentYear(Integer.parseInt(dependentUiViewModel.value));
                                } else {
                                    dropdownReasons.add(new DropdownReasons("-DD-", 0 + ""));
                                }

                                daysString = CommonUtils.getTextsFromDropdownReasons(dropdownReasons);
                                dependentSpinnerAdapter =
                                        new ArrayAdapter<String>(context, R.layout.spinner_custom, daysString);
                                uiViewModel.dropdownReasons = dropdownReasons;
                                spinner.setAdapter(dependentSpinnerAdapter);
                                spinner.setTag(uiViewModel);
                                spinner.setSelection((uiViewModel.value != null && !uiViewModel.value.equalsIgnoreCase("") && !uiViewModel.value.contains("dd")) ? Integer.parseInt(uiViewModel.value) : 0, true);  //must
                                spinner.setOnItemSelectedListener(spinnerListener);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();

                            }

                        }
                    }
                }
            }


            if (selectedPosition != -1) {
                spinner.setSelection(selectedPosition);
            } else if (uiViewModel.value != null && !uiViewModel.value.equalsIgnoreCase("")) {
                spinner.setSelection(CommonUtils.getPositionFromSpinnerBasedOnValueToId(uiViewModel.dropdownReasons, uiViewModel.value));
            }

            if (uiViewModel.disabled) {
                if (uiViewModel.dropdownReasons != null && !hasOnlySelectInDropdownReasons(uiViewModel.dropdownReasons))
                    spinner.setEnabled(true);
                else
                    spinner.setEnabled(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tvTitle.getText().toString().trim().matches("")) {
            tvTitle.setVisibility(View.GONE);
        }
        return view;
    }

    public static void setAdapterToDDfromMM(Context context, ViewGroup viewGroup, String value) {
        Spinner dependentSpinner = (Spinner) CommonUtils.getViewFromViewGroup(viewGroup, "dd", true);
        UiViewModel dependentUiViewModel = (UiViewModel) dependentSpinner.getTag();
        ArrayAdapter<String> dependentSpinnerAdapter = null;
        if (CommonUtils.isStringInteger(value)) {
            List<DropdownReasons> dropdownReasons = CommonUtils.getNoOfDaysFromPresentYear(Integer.parseInt(value));
            dependentUiViewModel.dropdownReasons = dropdownReasons;
        } else {
            dependentUiViewModel.dropdownReasons.clear();
            dependentUiViewModel.dropdownReasons.add(new DropdownReasons(context.getResources().getString(R.string.dd__), 0 + ""));
        }

        List<String> daysString = CommonUtils.getTextsFromDropdownReasons(dependentUiViewModel.dropdownReasons);
        dependentSpinnerAdapter =
                new ArrayAdapter<String>(context, R.layout.spinner_custom, daysString);
        dependentSpinner.setAdapter(dependentSpinnerAdapter);
        dependentSpinner.setTag(dependentUiViewModel);
    }

    public static View getDynamicRadioWithHorizontalView(Context context, final UiViewModel uiViewModel, final ViewGroup viewGroup) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        View view = vi.inflate(R.layout.selected_radio_group, null);
        final PresetRadioGroup radioGroup = (PresetRadioGroup) view.findViewById(R.id.radioGroup);
        final PresetValueButton presentRadio1 = (PresetValueButton) view.findViewById(R.id.radioButton1);
        final PresetValueButton presentRadio2 = (PresetValueButton) view.findViewById(R.id.radioButton2);
        final PresetValueButton presentRadio3 = (PresetValueButton) view.findViewById(R.id.radioButton3);

        final List<DropdownReasons> items = uiViewModel.dropdownReasons;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (i == 0) {
                    presentRadio1.setVisibility(View.VISIBLE);
                    presentRadio1.setValue(items.get(i).Text);
                    presentRadio1.setTag(items.get(i).Text);
                    presentRadio1.setTag(R.id.TagId, items.get(i));
                    if (items.get(i).Selected) {
                        radioGroup.check(R.id.radioButton1);
                        radioGroup.setTag(R.id.TagId, items.get(i));
                    }
                } else if (i == 1) {
                    presentRadio2.setVisibility(View.VISIBLE);
                    presentRadio2.setValue(items.get(i).Text);
                    presentRadio2.setTag(items.get(i).Text);
                    presentRadio2.setTag(R.id.TagId, items.get(i));
                    if (items.get(i).Selected) {
                        radioGroup.check(R.id.radioButton2);
                        radioGroup.setTag(R.id.TagId, items.get(i));
                    }
                } else if (i == 2) {
                    presentRadio3.setVisibility(View.VISIBLE);
                    presentRadio3.setValue(items.get(i).Text);
                    presentRadio3.setTag(items.get(i).Text);
                    presentRadio3.setTag(R.id.TagId, items.get(i));
                    if (items.get(i).Selected) {
                        radioGroup.check(R.id.radioButton3);
                        radioGroup.setTag(R.id.TagId, items.get(i));
                    }
                }
            }
        }

        //  if (uiViewModel.dependentViewKeysList != null && uiViewModel.dependentViewKeysList.size() > 0) {
        radioGroup.setOnCheckedChangeListener(new PresetRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View radioGroup, View radioButton, boolean isChecked, int checkedId) {
                radioGroup.setTag(R.id.TagId, radioButton.getTag(R.id.TagId));
                if (radioButton.getTag() != null && radioButton.getTag() instanceof String) {
                    String text = (String) radioButton.getTag();
                    if (text.equalsIgnoreCase("yes") && uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("isSSNAvailable")) {
                        for (UiViewModel.ViewExistenceModel viewExistenceModel : uiViewModel.dependentViewKeysList) {
                            View view1 = CommonUtils.getViewFromViewGroup(viewGroup, viewExistenceModel.keyName, viewExistenceModel.isKey);
                            if (view1 != null)
                                view1.setVisibility(View.VISIBLE);
                        }
                    } else if (text.equalsIgnoreCase("no") && uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("isSSNAvailable")) {
                        for (UiViewModel.ViewExistenceModel viewExistenceModel : uiViewModel.dependentViewKeysList) {
                            View view1 = CommonUtils.getViewFromViewGroup(viewGroup, viewExistenceModel.keyName, viewExistenceModel.isKey);
                            if (view1 != null) {
                                view1.setVisibility(View.GONE);
                                View view2 = CommonUtils.getViewFromViewGroup((ViewGroup) view1, viewExistenceModel.keyName, viewExistenceModel.isKey);
                                if (view2 instanceof EditText) {
                                    ((EditText) view2).setText("");
                                }
                            }
                        }
                    }
                }
            }
        });
        //  }

        if (uiViewModel.disabled)
            radioGroup.setEnabled(false);

        radioGroup.setTag(uiViewModel);
        return view;
    }


    //Method to Create Radio Button
    public static View getDynamicRadio(Context context, final UiViewModel uiViewModel, final ViewGroup viewGroup) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        View view = vi.inflate(R.layout.empty_layout, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearNonScrollableLayout);
        final RadioGroup radioGroup = new RadioGroup(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layout.addView(radioGroup, p);

        final List<DropdownReasons> items = uiViewModel.dropdownReasons;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                final RadioButton radioButtonView = new RadioButton(context);
                radioButtonView.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
                radioButtonView.setText(items.get(i).getText());
                radioButtonView.setTag(items.get(i).getText().toLowerCase());
                radioButtonView.setTag(R.id.TagId, items.get(i));
                radioButtonView.setButtonDrawable(context.getResources().getDrawable(R.drawable.custom_radio_button_new));
                radioButtonView.setPadding(10, 10, 10, 10);
                radioGroup.addView(radioButtonView, p);
            }

            if (uiViewModel.dependentViewKeysList != null && uiViewModel.dependentViewKeysList.size() > 0) {
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
                        if (radioButton.getTag() != null && radioButton.getTag() instanceof String) {
                            String text = (String) radioButton.getTag();
                            if (text.equalsIgnoreCase("yes") && uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("isSSNAvailable")) {
                                for (UiViewModel.ViewExistenceModel viewExistenceModel : uiViewModel.dependentViewKeysList) {
                                    View view1 = CommonUtils.getViewFromViewGroup(viewGroup, viewExistenceModel.keyName, viewExistenceModel.isKey);
                                    if (view1 != null)
                                        view1.setVisibility(View.VISIBLE);
                                }
                            } else if (text.equalsIgnoreCase("no") && uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("isSSNAvailable")) {
                                for (UiViewModel.ViewExistenceModel viewExistenceModel : uiViewModel.dependentViewKeysList) {
                                    View view1 = CommonUtils.getViewFromViewGroup(viewGroup, viewExistenceModel.keyName, viewExistenceModel.isKey);
                                    if (view1 != null)
                                        view1.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                });
            }
        }

        ArrayList<RadioButton> listOfRadioButtons = CommonUtils.getListOfRadioButtonsFromRadioGroup(radioGroup);
        for (int j = 0; j < uiViewModel.dropdownReasons.size(); j++) {
            DropdownReasons dropdownReasons = uiViewModel.dropdownReasons.get(j);
            if (dropdownReasons.Selected) {
                listOfRadioButtons.get(j).setChecked(true);
            }
        }

        if (uiViewModel.disabled)
            radioGroup.setEnabled(false);

        radioGroup.setTag(uiViewModel);
        return view;
    }

    //Method to Create Spinner
    public static ViewGroup getDynamicRadioWithExtraView(final Context context, UiViewModel uiViewModel) {
        List<RetrofitResponse.RejectTypeOptions> items = uiViewModel.rejectTypeOptions;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        final RadioGroup radioGroup = new RadioGroup(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(5, 0, 0, 0);
        layout.addView(radioGroup, p);

        //List<RetrofitResponse.RejectTypeOptions> items = uiViewModel.rejectTypeOptions;

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {

                RadioButton radioButtonView = new RadioButton(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    radioButtonView.setButtonDrawable(context.getResources().getDrawable(R.drawable.custom_radio_button_new));
                }
                radioButtonView.setPadding(10, 10, 10, 10);
                radioButtonView.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
                radioButtonView.setText(items.get(i).optionName);
                radioButtonView.setTag(items.get(i));
                if (items.get(i).disabled)
                    radioButtonView.setEnabled(false);
                radioGroup.addView(radioButtonView, p);
                RetrofitResponse.RejectTypeOptions rejectTypeOption = items.get(i);
                List<DropdownReasons> optionValues = rejectTypeOption.optionValues;
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                if (optionValues != null && optionValues.size() != 0) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(200)));
                    linearLayout.setPadding(dpToPx(10), dpToPx(5), dpToPx(10), dpToPx(10));
                    //linearLayout.setBackgroundColor(context.getResources().getColor(R.color.approval_color));

                    final ListView listView = new ListView(context);
                    listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        listView.setBackground(context.getDrawable(R.drawable.view_border));
                    }

                    final CustomDropdownModelAdapter customDropdownModelAdapter = new CustomDropdownModelAdapter(context);
                    for (int j = 0; j < optionValues.size(); j++) {
                        optionValues.get(j).idText = j;
                        customDropdownModelAdapter.add(optionValues.get(j), j);
                    }
                    listView.setAdapter(customDropdownModelAdapter);
                    linearLayout.addView(listView);
                    radioGroup.addView(linearLayout, params);
                    radioButtonView.setTag(R.id.TagId, linearLayout);
                    linearLayout.setVisibility(View.GONE);

                    listView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            listView.getParent().requestDisallowInterceptTouchEvent(true);
                            return false;
                        }
                    });

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            DropdownReasons dropdownReasons = (DropdownReasons) listView.getAdapter().getItem(i);
                            dropdownReasons.Selected = !dropdownReasons.Selected;
                            customDropdownModelAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int count = group.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View radioButton = group.getChildAt(i);
                        if (radioButton instanceof RadioButton) {
                            if (radioButton.getTag(R.id.TagId) != null) {
                                LinearLayout linearLayout = (LinearLayout) radioButton.getTag(R.id.TagId);
                                linearLayout.setVisibility(View.GONE);
                            }
                        }
                    }

                    int radioButtonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonId);
                    if (radioButton.getTag(R.id.TagId) != null) {
                        LinearLayout linearLayout = (LinearLayout) radioButton.getTag(R.id.TagId);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if (uiViewModel.disabled)
            radioGroup.setEnabled(false);

        radioGroup.setTag(uiViewModel);
        return layout;
    }


    //Get Left aligned Textview Title and Description
    public static View getLeftAlignedTitleAndDescription(final Context context, UiViewModel uiViewModel) {
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.detailspage_item, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.details_thiredline);
        tvTitle.setPadding(3, 0, 0, 5);
        tvTitle.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        TextView tvDescription = (TextView) view.findViewById(R.id.details_fourthline);
        tvDescription.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        tvDescription.setTextColor(ContextCompat.getColor(context, R.color.list_item_color));
        tvDescription.setPadding(3, 0, 0, 0);
        view.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
        view.findViewById(R.id.details_firstline).setVisibility(View.GONE);
        view.findViewById(R.id.details_secondline).setVisibility(View.GONE);

        if (uiViewModel != null) {
            setTextToDynamicLabel(tvTitle, uiViewModel);
            tvDescription.setText((uiViewModel.value != null &&
                    !uiViewModel.value.matches("")) ? Html.fromHtml(uiViewModel.value) : "");
        }

        if (tvTitle.getText().toString().matches("")) {
            tvTitle.setVisibility(View.GONE);
        }

        if (tvDescription.getText().toString().matches("")) {
            tvDescription.setText("-");
        }
        return view;
    }


    //Get Left aligned Textview Title and Description
    public static View getLeftAlignedSingleLabel(final Context context, UiViewModel uiViewModel) {
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.detailspage_item, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.details_thiredline);
        tvTitle.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        view.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
        view.findViewById(R.id.details_firstline).setVisibility(View.GONE);
        view.findViewById(R.id.details_secondline).setVisibility(View.GONE);
        view.findViewById(R.id.details_fourthline).setVisibility(View.GONE);
        if (uiViewModel != null) {
            setTextToDynamicLabel(tvTitle, uiViewModel);
        }

        return view;
    }

    //Get Left aligned Header Label
    public static View getLeftAlignedHeaderLabel(final Context context, String title) {
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.detailspage_item, null);
        TextView textViewHeader = (TextView) view.findViewById(R.id.details_firstline);
        LinearLayout subLinearLayout = (LinearLayout) view.findViewById(R.id.subLinearLayout);
        subLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.gotit_color));
        subLinearLayout.setPadding(10, 5, 5, 5);

        view.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
        view.findViewById(R.id.details_secondline).setVisibility(View.GONE);
        view.findViewById(R.id.details_thiredline).setVisibility(View.GONE);
        view.findViewById(R.id.details_fourthline).setVisibility(View.GONE);

        textViewHeader.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        textViewHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                context.getResources().getDimension(R.dimen.font_size));

        if (stringContainsHTML(title))
            textViewHeader.setText(Html.fromHtml(title));
        else
            textViewHeader.setText(title);
        return view;
    }

    //Get Left aligned Header Label
    public static View getCenterAlignedHeaderLabel(final Context context, String title) {
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.detailspage_item, null);
        TextView textViewHeader = (TextView) view.findViewById(R.id.details_firstline);
        textViewHeader.setHeight(80);
        textViewHeader.setPadding(0, 0, 0, 15);
        textViewHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                context.getResources().getDimension(R.dimen.action_titile));
        textViewHeader.setHeight(CommonUtils.dpToPx(40));
        textViewHeader.setGravity(Gravity.CENTER);
        textViewHeader.setTextColor(ContextCompat.getColor(context, R.color.gotit_color));
        view.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
        view.findViewById(R.id.details_secondline).setVisibility(View.GONE);
        view.findViewById(R.id.details_thiredline).setVisibility(View.GONE);
        view.findViewById(R.id.details_fourthline).setVisibility(View.GONE);
        view.findViewById(R.id.viewLine).setVisibility(View.GONE);

        if (stringContainsHTML(title))
            textViewHeader.setText(Html.fromHtml(title));
        else
            textViewHeader.setText(title);
        return view;
    }

    //Get Normal Label with and without background
    public static View getLeftAlignedNormalLabel(final Context context, final UiViewModel uiViewModel, String textColor) {
        String title = uiViewModel.name;
        View view;
        createRequirementPage = new CreateRequirementPageNew();
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert vi != null;
        view = vi.inflate(R.layout.detailspage_item, null);
        TextView tvLabel = (TextView) view.findViewById(R.id.details_firstline);
        tvLabel.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
        view.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
        view.findViewById(R.id.details_secondline).setVisibility(View.GONE);
        view.findViewById(R.id.details_thiredline).setVisibility(View.GONE);
        view.findViewById(R.id.details_fourthline).setVisibility(View.GONE);

        if (uiViewModel.isHasInfo) {
            view.findViewById(R.id.details_firstline).setVisibility(View.GONE);
            final RelativeLayout relativeInfo = (RelativeLayout) view.findViewById(R.id.relativeInfo);
            relativeInfo.setVisibility(View.VISIBLE);
            tvLabel = (TextView) view.findViewById(R.id.tvInfoTitle);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgInfo);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    CommonUtils.hideKeyboard((Activity) context);
                    relativeInfo.getParent().requestChildFocus(relativeInfo, view);
                    view.requestFocus();
                    final Runnable r = new Runnable() {
                        public void run() {
                            if (uiViewModel.warningText != null && !uiViewModel.warningText.equalsIgnoreCase("")) {
                                ToolTipWindowForList popup = new ToolTipWindowForList(context, 1, uiViewModel.warningText);
                                //Rect location=popup.locateView(view);
                                popup.show(view);

                            }
                        }
                    };
                    handler.postDelayed(r, 500);
                }
            });
        }

        if (textColor.equalsIgnoreCase("redOnlyLabel")) {
            tvLabel.setTextColor(ContextCompat.getColor(context, R.color.list_item_jobtiel_color));
            tvLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.red_text_bg));
        } else if (textColor.equalsIgnoreCase("black")) {
            tvLabel.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else {
            tvLabel.setTextColor(ContextCompat.getColor(context, R.color.black_overlay));
            // tvLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_text_bg));
        }

        //  String titleUpdated = removeTags(title);
        /*if (stringContainsHTML(title))
            tvLabel.setText((uiViewModel.inputType.matches("")?Html.fromHtml(uiViewModel.value):Html.fromHtml(title)));
        else {
            tvLabel.setText((uiViewModel.inputType.matches("") ? uiViewModel.value : title));
        }*/
        if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("LitStillHavingIssues"))
            tvLabel.setText(Html.fromHtml(title));
        else
            tvLabel.setText((uiViewModel.inputType != null &&
                    !uiViewModel.inputType.matches("") ? Html.fromHtml(title) : Html.fromHtml(uiViewModel.value)));
        return view;
    }

    //Get Normal Label or Textbox
    public static View getLabelOrLabelWithEditText(final Context context, UiViewModel uiViewModel, ViewGroup viewGroup) {
        boolean noNeedEditText = true, hasTitleAndDesc = true;
        if (uiViewModel.inputType == null) {
            noNeedEditText = !uiViewModel.key.equalsIgnoreCase("LblUsername");
        } else if (uiViewModel.inputType.matches("SecurityQuestions")) {
            noNeedEditText = false;
        }/* else if (uiViewModel.disabled) {
            noNeedEditText = true;
            hasTitleAndDesc = true;
        }*/ else if (uiViewModel.inputType.equalsIgnoreCase("TextBox") || uiViewModel.inputType.equalsIgnoreCase("text")) {
            noNeedEditText = false;
        }

        if (!noNeedEditText) {
            if (uiViewModel != null) {
                View view = null;
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert vi != null;
                view = vi.inflate(R.layout.view_textbox, null);
                CustomTextView label = (CustomTextView) view.findViewById(R.id.contractTextView);
                label.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
                LinearLayout parentLayout = (LinearLayout) view.findViewById(R.id.totalContractValueHeader);
                parentLayout.setPadding(12, 5, 0, 0);
                if (uiViewModel.name != null && !uiViewModel.name.matches("")) {
                    String name = (uiViewModel.name != null) ? uiViewModel.name.replace(":", " ") : "";
                    if (uiViewModel.required) {
                        if (stringContainsHTML(name))
                            label.setText(Html.fromHtml(name));
                        label.setText(ApprovalFontCommons.maditoryStringlable(name));
                    } else {
                        if (stringContainsHTML(uiViewModel.name))
                            label.setText(Html.fromHtml(name));
                        else
                            label.setText(name);
                    }
                }
                CustomEditText CustomEditText = (CustomEditText) view.findViewById(R.id.totalcontractvalue_edt);
                CustomEditText.setPadding(5, 10, 0, 10);
                CustomEditText.setTextSize(context.getResources().getInteger(R.integer.labels_common_font));
                if (uiViewModel.disabled) {
                    CustomEditText.setFocusable(false);
                    CustomEditText.setFocusableInTouchMode(false);
                    CustomEditText.setBackgroundResource(R.drawable.empty_cell_withoutborder);
                }

                if (uiViewModel.textType.equalsIgnoreCase(context.getString(R.string.pwd_text_type))) {
                    CustomEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else if (uiViewModel.key.equalsIgnoreCase("last4ssn")) {
                    CustomEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    CustomEditText = CommonUtils.setEditTextMaxLength(CustomEditText, 4);
                } else if (uiViewModel.key.equalsIgnoreCase("CashAdvAmount")) {
                    CustomEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789.,"));
                } else if (uiViewModel.key.equalsIgnoreCase("homephone") ||
                        uiViewModel.key.equalsIgnoreCase("emergencyContact1Phone") ||
                        uiViewModel.key.equalsIgnoreCase("emergencyContact2Phone")) {
                    CustomEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    CustomEditText = CommonUtils.setEditTextMaxLength(CustomEditText, context.getResources().getInteger(R.integer.phoneLength));
                    CommonUtils.callEditTextTextFormatWatcher("***-***-****", CustomEditText, false);
                } else if (uiViewModel.key.equalsIgnoreCase("email")) {
                    CustomEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                } else if (uiViewModel.key.equalsIgnoreCase("zip")) {
                    CustomEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    CustomEditText = CommonUtils.setEditTextMaxLength(CustomEditText, context.getResources().getInteger(R.integer.zipLength));
                } else if (uiViewModel.key.equalsIgnoreCase("candidateSSN") || uiViewModel.key.equalsIgnoreCase("candidateSSNConfirm")) {
                    CustomEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    CommonUtils.callEditTextTextFormatWatcher(uiViewModel.maskedText, CustomEditText, true);
                } else
                    CustomEditText.setInputType(InputType.TYPE_CLASS_TEXT);

                if (uiViewModel.key != null && uiViewModel.key.equalsIgnoreCase("candidateSSNConfirm")) {
                    CustomEditText.setTag(R.id.dependentUiObject,
                            CommonUtils.getViewFromViewGroup(viewGroup, "candidateSSN", true));
                }
                if (uiViewModel.textLength != 0) {
                    CustomEditText = CommonUtils.setEditTextMaxLength(CustomEditText, uiViewModel.textLength);
                }
                CustomEditText.setTag(uiViewModel);

                if (uiViewModel.key != null)
                    parentLayout.setTag(uiViewModel);

                if (uiViewModel.value != null) {
                    CustomEditText.setText(uiViewModel.value);
                }
                return view;
            } else {
                return new View(context);
            }
        } else {
            /*if (hasTitleAndDesc)
                return getLeftAlignedTitleAndDescription(context, uiViewModel);
            else
                return getLeftAlignedNormalLabel(context, uiViewModel, "");*/
            return getLeftAlignedTitleAndDescription(context, uiViewModel);
        }
    }


    /*------------ Method to create autocompletetextview with input text view using layout------------------*/
    public static View getTypeAHeadView(final Context context, final JSONObject jsonObject, final ArrayList<String> hiringmanager) throws JSONException {

        createRequirementPage = new CreateRequirementPageNew();
        nContext = context;
        View view = null;
        LayoutInflater vi = (LayoutInflater) nContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = vi.inflate(R.layout.view_autocomplete_text, null);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleTextView);
        textInputLayout = (TextInputLayout) view.findViewById(R.id.textInputLayout);
        int randomNumber = randomGenerateMethod(600, 699);
        methodToAddResponseToArray(jsonObject, randomNumber);
        autoCompleteTextView.setTag(randomNumber);
        Spanned hintvalu;
        if (jsonObject.getString("isMandotary").equalsIgnoreCase("Yes")) {
            hintvalu = new ApprovalFontCommons(nContext).applicantquestions_manditoryString(jsonObject.getString("value"), nContext);
        } else {
            hintvalu = ApprovalFontCommons.nonmaditoryStringlable(jsonObject.getString("value"));
        }
        if (jsonObject.getString("isReadOnly").equalsIgnoreCase("YES"))
            autoCompleteTextView.setEnabled(false);
        textInputLayout.setHint(hintvalu);
        JSONArray default_values_jsonArray = null;
        if (!jsonObject.getString("DefaultValues").equalsIgnoreCase("null"))
            default_values_jsonArray = new JSONArray(jsonObject.getString("DefaultValues"));
        if (default_values_jsonArray != null && default_values_jsonArray.length() > 0) {
            autoCompleteTextView.setText(default_values_jsonArray.getString(0));
            finalMaptoSubmit.put(jsonObject.getString("name"), "" + default_values_jsonArray.getString(0));
             autoCompleteTextvalue.put(randomNumber,default_values_jsonArray.getString(0)) ;
        }



        /*----------------Text Change listener in autocomplete textview for indivual references--------------------*/
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*--------------Identifying the tag of each element.-------------*/
                AutoCompleteTextView autoCompleteTextView12 = autoCompleteTextView;
                int tag = (int) autoCompleteTextView12.getTag();
                for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                    int key = objCRDView.getTagValueCR();
                    if (tag == key) {
                        shouldAutoComplete = true;
                        if (autoCompleteTextvalue != null) {
                            //for (int position = 0; position < adapter.getCount(); position++) {
                                if (autoCompleteTextvalue.keySet().contains(key)&&autoCompleteTextvalue.get(key).equalsIgnoreCase(charSequence.toString())) {
                                    shouldAutoComplete = false;
                                    break;
                                }
                            //}
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (shouldAutoComplete) {
                    AutoCompleteTextView autoCompleteTextView12 = autoCompleteTextView;
                    int tag = (int) autoCompleteTextView12.getTag();
//                    Toast.makeText(nContext, "Reference ID of This AutocompleteTextView == " + tag, Toast.LENGTH_LONG).show();
                    for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                        int key = objCRDView.getTagValueCR();
                        if (tag == key && editable.toString().trim().length() > 0) {
                            if (objCRDView.getActionNameCR().equals("HiringManagerWithEmployeeID") || objCRDView.getActionNameCR().equals("HiringManagerWithEmployeeID?Type=H")) {
                                String action = "HiringManager";
                                createRequirementPage.getEventListnerinHireingManager(nContext, editable.toString(), autoCompleteTextView, action.split("[\\.,\\s!;?:\"']+"));
                            } else {
                                createRequirementPage.getEventListnerinHireingManager(nContext, editable.toString(), autoCompleteTextView, objCRDView.getActionNameCR().split("[\\.,\\s!;?:\"']+"));
                            }
                            adapter = new ArrayAdapter<String>(nContext, R.layout.spinner_custom, createRequirementPage.hiringManagerr);
                            autoCompleteTextView12.setThreshold(1);
                            autoCompleteTextView12.setAdapter(adapter);


                        }
                    }
                }


            }
        });


        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (autoCompleteTextvalue.keySet().contains(autoCompleteTextView.getTag())&&!autoCompleteTextvalue.get(autoCompleteTextView.getTag()).equalsIgnoreCase(autoCompleteTextView.getText().toString())) {
                        autoCompleteTextView.setText(""); // clear your TextView
                        try {
                            Commons.hiringManagerID = 0;
                            finalMaptoSubmit.put(jsonObject.getString("name"), null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                AutoCompleteTextView autoCompleteTextView12 = autoCompleteTextView;
                int tag = (int) autoCompleteTextView12.getTag();
                for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                    int key = objCRDView.getTagValueCR();
                    if (tag == key) {
                        int index = createRequirementPage.hiringManagerr.indexOf((autoCompleteTextView.getText().toString()));
                        Commons.hiringManagerID = Integer.parseInt(createRequirementPage.hiringManagerId[index]);
                        try {
                            finalMaptoSubmit.put(jsonObject.getString("name"), "" + Commons.hiringManagerID);
                            autoCompleteTextvalue.put(key, autoCompleteTextView.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (objCRDView.getActionNameCR().equalsIgnoreCase("WorkLocation")) {
                            for (CreateRquirementDynamicViewObjects nextObjCRDView : dynamicViewObjectsArrayList) {
                                String name = nextObjCRDView.getNameCR();

                                if (name.equalsIgnoreCase("workLocationAddress")) {
                                    try {
                                        JSONObject jsonObject1 = new JSONObject();
                                        //jsonObject1.put("MspId", Commons.msp_id);
                                        //jsonObject1.put("ClientId", Commons.client_id);
                                        jsonObject1.put("WorkLocationId", Commons.hiringManagerID);
                                        new ST_RequestService(context).loadPostRequest(Commons.webapi_domain_url + context.getString(R.string.webapi_urn_5) + "/FetchDefaultWorkLocationAddress", jsonObject1);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }

                        }
                        if ((objCRDView.getActionNameCR().equalsIgnoreCase("CostCenterNumber") || objCRDView.getActionNameCR().contains("CostCenterNumber")) && (objCRDView.getNameCR().equalsIgnoreCase("CostCenterId"))) {
                            try {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("MspId", Commons.msp_id);
                                jsonObject1.put("ClientId", Commons.client_id);
                                jsonObject1.put("keywords", autoCompleteTextView12.getText());
                                new GetCostCentervalue(Commons.webapi_domain_url + context.getString(R.string.webapi_urn_3) + "/CostCenterNumber", jsonObject1).execute();

//                                new ST_RequestService(context).loadPostRequest(Commons.webapi_domain_url + context.getString(R.string.webapi_urn_3) + "/CostCenterNumber", jsonObject1);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

//                                    break;
                        }


                    }
                }

            }
        });


        autoCompleteTextViewHashMap.put(randomNumber, autoCompleteTextView);
        return view;
    }


    private static class GetCostCentervalue extends AsyncTask<Void, Void, Void> {
        String req_url;
        int key;
        JSONObject obj;
        CommonHelper common_helper_obj;
        ProgressDialog dialog;
        String result_str;

        public GetCostCentervalue(String list_request_url,
                                  JSONObject calling_josnobject) {
            this.req_url = list_request_url;
            this.obj = calling_josnobject;

            common_helper_obj = new CommonHelper();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (common_helper_obj != null && nContext != null)
                dialog = common_helper_obj.showDialog(nContext);
        }

        protected Void doInBackground(Void... params) {
            result_str = GetDataByURL.postData(req_url, obj);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!result_str.equals("") || result_str.length() != 0) {
                CreateRequirementPageNew.loadDependentData(result_str);
            }
            if (dialog != null)
                dialog.dismiss();
        }

    }


  /*  private boolean isPtojectExistes(String slect, ArrayList<String> adapterTyperhead) {
        boolean reponse = false;
        if (adapterTyperhead != null) {
            if (adapterTyperhead.contains(slect)) {
                reponse = true;
            }
        }

        return reponse;
    }*/

    /*-----Creaating the view for date picker using datepicker dialog---------*/
    public static View getDatePicker(final Context context, final JSONObject jsonObject1, JSONArray mEndDatePartitions) {
        dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.view_datepicker, null);
        final CustomTextView datePickerTextView = (CustomTextView) view.findViewById(R.id.FieldStartDate_test);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.radioBtnsLayout);
        final AutoCompleteTextView datePickAutoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.fieldStartTextView);


        try {
            if (jsonObject1.getString("isMandotary").equalsIgnoreCase("Yes")) {
                datePickerTextView.setText(new ApprovalFontCommons(context).applicantquestions_manditoryString(jsonObject1.getString("value"), context));

            } else {
                datePickerTextView.setText(jsonObject1.getString("value"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button btn = (Button) view.findViewById(R.id.btnDatePicker);
        int randomNumber = randomGenerateMethod(700, 799);
        btn.setTag(randomNumber);
        datePickAutoCompleteTextView.setTag(randomNumber);

        buttonHashMap.put(randomNumber, btn);
        buttonAutoCompleteTextView.put(randomNumber, datePickAutoCompleteTextView);
        methodToAddResponseToArray(jsonObject1, randomNumber);

        try {

            if (jsonObject1.get("name").equals("enddate") && jsonObject1.getString("isDisplay").equalsIgnoreCase("YES")) {
                linearLayout.setVisibility(View.VISIBLE);
                endDateAutoCompleteId = randomNumber;
                months_3 = (Button) view.findViewById(R.id.moths3);
                months_6 = (Button) view.findViewById(R.id.moths6);
                months_9 = (Button) view.findViewById(R.id.moths9);
                months_12 = (Button) view.findViewById(R.id.moths12);
                months_3.setText(mEndDatePartitions.getString(0));
                months_6.setText(mEndDatePartitions.getString(1));
                months_9.setText(mEndDatePartitions.getString(2));
                months_12.setText(mEndDatePartitions.getString(3));
                months_3.setOnClickListener(topButtonsListener);
                months_6.setOnClickListener(topButtonsListener);
                months_9.setOnClickListener(topButtonsListener);
                months_12.setOnClickListener(topButtonsListener);
                Commons.endDate = "";

                // datePickAutoCompleteTextView.setText(Commons.endDate);
            } else {


                JSONArray jarray = jsonObject1.getJSONArray("DefaultValues");
//               String fixedStartDate =  jarray.getString(0).trim();
                showDateModel = DateTimePickerUtil.splitStringIntoDateModel(jarray.getString(0).trim().toString());
                startDate = jarray.getString(0).trim();
                datePickAutoCompleteTextView.setText(startDate);
                linearLayout.setVisibility(View.GONE);
                finalMaptoSubmit.put(jsonObject1.getString("name"), jarray.getString(0).trim());
                defaultDate = new Date();
                defaultDate = dateFormatter.parse(jarray.getString(0).trim());

                if (jsonObject1.getString("isReadOnly").equalsIgnoreCase("YES"))
                    datePickAutoCompleteTextView.setEnabled(false);
            }
            datePickAutoCompleteTextView.setFocusable(false);
            datePickAutoCompleteTextView.setFocusableInTouchMode(false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*-------Code for creating the datepicker view------*/
                Button b = (Button) view;
                int tag = (int) b.getTag();
                for (Map.Entry<Integer, Button> mapEntry : buttonHashMap.entrySet()) {
                    int key = mapEntry.getKey();
//                    AutoCompleteTextView autoCompleteTextView13 = buttonAutoCompleteTextView.get(tag);
                    if (tag == key) {

                        for (int k = 0; k < dynamicViewObjectsArrayList.size(); k++) {
                            if (dynamicViewObjectsArrayList.get(k).getTagValueCR() == tag) {
                                if (dynamicViewObjectsArrayList.get(k).getNameCR().equalsIgnoreCase("enddate")) {
                                    endDateClicked = false;
                                } else {
                                    endDateClicked = true;
                                }
                            }
                        }
//                        Toast.makeText(context,autoCompleteTextView13.getText().toString(),Toast.LENGTH_LONG).show();
                     /*   if (autoCompleteTextView13.getText().toString().length() > 0) {

                            setDateTimeField(context, autoCompleteTextView13.getText().toString(), tag, endDateClicked);
                        } else {*/
                        setDateTimeField(context, startDate, tag, endDateClicked);
//                        }


                    }
                }
            }
        });


        return view;
    }

    private static void resetButtonVisibility() {
        months_3.setSelected(false);
        months_3.setTextColor(Color.BLACK);
        months_6.setSelected(false);
        months_6.setTextColor(Color.BLACK);
        months_9.setSelected(false);
        months_9.setTextColor(Color.BLACK);
        months_12.setSelected(false);
        months_12.setTextColor(Color.BLACK);

    }


    /*--------Creting the view for texbox-------------*/
    public static View getTextBox(final Context context, final JSONObject jsonObject) throws JSONException {
        View view = null;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = vi.inflate(R.layout.view_textbox, null);
        CustomTextView contractValue_tv = (CustomTextView) view.findViewById(R.id.contractTextView);
        final CustomEditText contractValue_ET_ = (CustomEditText) view.findViewById(R.id.totalcontractvalue_edt);
        int randomNumber = randomGenerateMethod(800, 899);
        contractValue_ET_.setTag(randomNumber);
        editetextboxHashmap.put(randomNumber, contractValue_ET_);
        methodToAddResponseToArray(jsonObject, randomNumber);

        if (jsonObject.getString("isMandotary").equalsIgnoreCase("Yes")) {
            contractValue_tv.setText(new ApprovalFontCommons(context).applicantquestions_manditoryString(jsonObject.getString("value"), context));
        } else {
            contractValue_tv.setText(ApprovalFontCommons.nonmaditoryStringlable(jsonObject.getString("value")));
        }
        if (jsonObject.getString("isReadOnly").equalsIgnoreCase("YES") && Commons.client_id == 30)
            contractValue_ET_.setEnabled(false);
       /* if(jsonObject.getString("name").equalsIgnoreCase("CostCenterName"))
            contractValue_ET_.setInputType(InputType.TYPE_CLASS_TEXT) ;
        else
            contractValue_ET_.setInputType(InputType.TYPE_CLASS_NUMBER);*/
        JSONArray default_values_jsonArray = null;
        if (!jsonObject.getString("DefaultValues").equalsIgnoreCase("null"))
            default_values_jsonArray = new JSONArray(jsonObject.getString("DefaultValues"));
        if (default_values_jsonArray != null && default_values_jsonArray.length() > 0) {
            contractValue_ET_.setText(default_values_jsonArray.getString(0));
            finalMaptoSubmit.put(jsonObject.getString("name"), "" + default_values_jsonArray.getString(0));
        }

        contractValue_ET_.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                    Toast.makeText(nContext, "Reference ID of This AutocompleteTextView == " + tag, Toast.LENGTH_LONG).show();
                try {
                   /* if(hasFocus == true)
                    {
                        Toast.makeText(context,"TEST DATA",Toast.LENGTH_LONG).show();
                    }
*/

                    if ((!hasFocus)) {
                        CustomEditText editext = contractValue_ET_;
                        for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {

                            String key = objCRDView.getNameCR();
                            CustomEditText editText = contractValue_ET_;
                            int tag = (int) editText.getTag();
                            String payrate = editText.getText().toString().trim();
                            if (key.equalsIgnoreCase("lblPayRateText") && payrate.length() > 0) {

                                finalMaptoSubmit.put(jsonObject.getString("name"), "" + payrate);

                                viewCreated = true;
                                loadpayrate_dependencyedittext();

                            } else if (key.equalsIgnoreCase("lblPayRateText") && payrate.length() == 0) {
                                finalMaptoSubmit.put(jsonObject.getString("name"), "");
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
               /* if((hasFocus==false)&&(contractValue_ET.getText().length()>0)){
                    String request_url = Commons.webapi_domain_url+ context.getString(R.string.webapi_urn_2)+"/CalculateRatesOnChangeofsupplierRegPayRate";
                   JSONObject jsonObjectkeys = mApprovalCommonRequestJSONObject.CalculateRatesOnChangeofsupplierRegPayRate(rate_1_edite.getText().toString(),"0");
                    new SubmiteToReuirement.GetContractValueData(request_url,jsonObjectkeys,1).execute();
                }*/

            }


        });


        return view;
    }

    public static void loadpayrate_dependencyedittext() {
        CustomEditText payrate = null, billrate = null, totalcontractvalu = null, weeklyspend = null;
        for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
            String key = objCRDView.getNameCR();
            int tag = objCRDView.getTagValueCR();
            switch (key) {
                case "lblPayRateText":
                    payrate = editetextboxHashmap.get(objCRDView.getTagValueCR());
                    break;
                case "txtBillRate":
                    billrate = editetextboxHashmap.get(objCRDView.getTagValueCR());
                    break;
                case "estContractValue":
                    totalcontractvalu = editetextboxHashmap.get(objCRDView.getTagValueCR());
                    break;
                case "WeeklySpendValue":
                    weeklyspend = editetextboxHashmap.get(objCRDView.getTagValueCR());
                    break;
            }


        }

        createRequirementPage.singleFieldayrateCaliculation(nContext, payrate, billrate, totalcontractvalu, weeklyspend);
    }


    /*------------ Method to create textbox-textbox with programatically------------------*/
    public static View getDualTextBoxView(final Context context, final JSONObject jsonObject) throws JSONException {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.view_dual_textbox, null);
        viewCreated = true;

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.payrateHeader);
        CustomTextView valueTextView = (CustomTextView) view.findViewById(R.id.payrate_tv1);
        final CustomEditText dual_billrate_et1 = (CustomEditText) view.findViewById(R.id.billrate_edt1);
        final CustomEditText dual_billrate_et2 = (CustomEditText) view.findViewById(R.id.billrate_edt2);


        //int randomNumber = randomGenerateMethod(900, 999);
        linearLayout.setTag(DUALTEXTBOX_TAG);
        dualtextboxHashmap.put("" + DUALTEXTBOX_TAG, linearLayout);

//        int randomNumber = 998;
//        billrate_et2.setTag(randomNumber);
        methodToAddResponseToArray(jsonObject, DUALTEXTBOX_TAG);
        DUALTEXTBOX_TAG++;
        if (jsonObject.getString("isReadOnly").equalsIgnoreCase("YES")) {
            dual_billrate_et1.setEnabled(false);
            dual_billrate_et2.setEnabled(false);
        }


        if (jsonObject.getString("isMandotary").equalsIgnoreCase("Yes")) {

            valueTextView.setText(new ApprovalFontCommons(context).manditoryString(jsonObject.getString("value"), context));
        } else {
            valueTextView.setText(ApprovalFontCommons.nonmaditoryStringlable(jsonObject.getString("value")));
        }
        JSONArray jarray = JSONUtils.getJSONArray(jsonObject.toString(), "DefaultValues");
        if (jarray.length() > 0) {
            dual_billrate_et1.setText(jarray.getString(0));
            dual_billrate_et2.setText(jarray.getString(1));

            if (jarray.getString(0).length() > 0) {
                if (Commons.billRateISEnable) {
                    Commons.billRateLow = Float.parseFloat(jarray
                            .getString(0));
                } else {
                    Commons.payRateLow = Float.parseFloat(jarray
                            .getString(0));
                }
            }
            if (jarray.getString(1).length() > 0) {
                if (Commons.billRateISEnable) {
                    Commons.billRateHigh = Float.parseFloat(jarray
                            .getString(1));
                } else {
                    Commons.payRateHigh = Float.parseFloat(jarray
                            .getString(1));
                }

            }
        }


        if (jsonObject.getString("name")
                .equalsIgnoreCase("txtBillRate")) {
            //Means bill rate is Enabled
            Commons.billRateISEnable = true;
            try {

                finalMaptoSubmit.put("BillRateLow", dual_billrate_et1.getText().toString());
                finalMaptoSubmit.put("BillRateHigh", dual_billrate_et2.getText().toString());
            } catch (Exception e) {
                finalMaptoSubmit.put("BillRateLow", "");
                finalMaptoSubmit.put("BillRateHigh", "");
            }

        } else {
            // Means Payrate is enabled
            Commons.billRateISEnable = false;
            try {

                finalMaptoSubmit.put("PayRateLow", dual_billrate_et1.getText().toString());
                finalMaptoSubmit.put("PayRateHigh", dual_billrate_et2.getText().toString());
            } catch (Exception e) {
                finalMaptoSubmit.put("PayRateLow", "");
                finalMaptoSubmit.put("PayRateHigh", "");
            }
        }
        /***********************************handle focus Event*********************/

        dual_billrate_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    LinearLayout layout = (LinearLayout) dual_billrate_et1.getParent();
                    int tag = (int) layout.getTag();
//                    Toast.makeText(context, "" + tag, Toast.LENGTH_LONG).show();
                    if (jsonObject.getString("name").equalsIgnoreCase("txtBillRate")) {
                        if (!dual_billrate_et1.getText().toString().equals("0"))
                            finalMaptoSubmit.put("BillRateLow", dual_billrate_et1.getText().toString());
                        else
                            finalMaptoSubmit.put("BillRateLow", "");

                    } else {

                        if (!dual_billrate_et1.getText().toString().equals("0"))
                            finalMaptoSubmit.put("PayRateLow", dual_billrate_et1.getText().toString());
                        else
                            finalMaptoSubmit.put("PayRateLow", "");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dual_billrate_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (jsonObject.getString("name")
                            .equalsIgnoreCase("txtBillRate")) {
                        if (!dual_billrate_et2.getText().toString().equals("0"))
                            finalMaptoSubmit.put("BillRateHigh", dual_billrate_et2.getText().toString());
                        else
                            finalMaptoSubmit.put("BillRateHigh", "");
                    } else {

                        if (!dual_billrate_et2.getText().toString().equals("0"))
                            finalMaptoSubmit.put("PayRateHigh", dual_billrate_et2.getText().toString());
                        else
                            finalMaptoSubmit.put("PayRateHigh", "");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /***********************************handle focus Event*********************/
        dual_billrate_et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                LinearLayout layout = (LinearLayout) dual_billrate_et2.getParent();
                int tag = (int) layout.getTag();

                LinearLayout layout1 = (LinearLayout) billrate_et2.getParent();
                int tag1 = (int) layout1.getTag();


                if ((!hasFocus) && (dual_billrate_et2.getText().length() > 0)) {
//                    Toast.makeText(context, billrate_et2.getText().toString(), Toast.LENGTH_LONG).show();

                    // if (tag == tag1) {

                    if (dual_billrate_et1.getText().toString().length() > 0 && dual_billrate_et2.getText().toString().length() > 0) {
                        Commons.rateHigh = dual_billrate_et2.getText().toString();
                        Commons.rateLow = dual_billrate_et1.getText().toString();

                        Commons.billRateHigh = Double.parseDouble(Commons.rateHigh);
                        Commons.billRateLow = Double.parseDouble(Commons.rateLow);
                        if (tag == tag1) {
                            if (Commons.billRateISEnable)
                                createRequirementPage.loadBillRate(0, context, dual_billrate_et1, dual_billrate_et2, getTotalEstimatevalue());
                            else
                                createRequirementPage.loadPayratTotalcontract(context, dual_billrate_et1, dual_billrate_et2, getTotalEstimatevalue());
                        } else {
                            createRequirementPage.loadPayratTotalcontract(context, dual_billrate_et1, dual_billrate_et2, getTotalEstimatevalue());

                        }
                    } else {
                        Commons.rateLow = "0";
                        Commons.rateHigh = "0";
                    }
                    Commons.neededStartDate = startDate;
                }
            }
        });


        billrate_et1 = dual_billrate_et1;
        billrate_et2 = dual_billrate_et2;

        return view;
    }

    /***********Creating the view for spinner******************/
    public static View getSpinnerView(Context context, JSONObject jsonObject) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.view_spinner, null);
        final Spinner currency_Spinner = (Spinner) view.findViewById(R.id.currency_spin);
        CustomTextView currencyTextView = (CustomTextView) view.findViewById(R.id.currency_tv);
        currencyTextView.setPadding(10, 0, 0, 0);
        currency_Spinner.setPadding(5, 10, 10, 10);
        JSONArray currencyValList;
        final ArrayList<String> currency_List = new ArrayList<String>();
        try {
            String tv_Val = jsonObject.getString("value");
            Spanned hintvalu = null;
            if (jsonObject.getString("isMandotary").equalsIgnoreCase("Yes")) {
                hintvalu = new ApprovalFontCommons(context).applicantquestions_manditoryString(tv_Val, context);
            } else {
                hintvalu = ApprovalFontCommons.nonmaditoryStringlable(tv_Val);
            }
            currencyTextView.setText(hintvalu);
            currencyValList = jsonObject.getJSONArray("DefaultValues");
            for (int j = 0; j < currencyValList.length(); j++) {
                String object = currencyValList.get(j).toString();
                currency_List.add(object);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_custom, currency_List);
            currency_Spinner.setAdapter(adapter);
            currency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    currency_Spinner.setSelection(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            if (jsonObject.getString("isReadOnly").equalsIgnoreCase("YES"))
                currency_Spinner.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }

    /*---------Creating and setting the date and time for date picker------*/
    private static void setDateTimeField(Context context, String dateString, final int tag, Boolean endDateAction) {

        int year = 0, month = 0, dateofMonth = 0;
        final DateTimeModel[] fromDateTimeModel = new DateTimeModel[1];

        Calendar newCalendar = Calendar.getInstance();
        if (!dateString.equalsIgnoreCase("")) {
            fromDateTimeModel[0] = DateTimePickerUtil.splitStringIntoDateModel(dateString);
            year = Integer.parseInt(dateString.split("/")[2]);
            month = Integer.parseInt(dateString.split("/")[0]) - 1;
            dateofMonth = Integer.parseInt(dateString.split("/")[1]);

        } else {
            year = Calendar.YEAR;
            month = Calendar.MONTH;
            dateofMonth = Calendar.DAY_OF_MONTH;
        }
        final int finalYear = year;
        final int finalMonth = month;
        final int finalDate = dateofMonth;

        DateTimePickerUtil.pickCalenderUponConditions((Activity) context,
                showDateModel,
                fromDateTimeModel[0],
                null,
                new GetDateModelListener() {
                    @Override
                    public void getDateModel(DateTimeModel dateModel) {
                        showDateModel = dateModel;
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(dateModel.getYear(), dateModel.getMonth() - 1, dateModel.getDate());
                        for (Map.Entry<Integer, AutoCompleteTextView> mapEntry : buttonAutoCompleteTextView.entrySet()) {
                            int key = mapEntry.getKey();
                            if (tag == key) {
                                AutoCompleteTextView autoCompleteTextView12 = mapEntry.getValue();
//                                autoCompleteTextView12.setText(dateFormatter.format(newDate.getTime()));
                                for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                                    int localTag = objCRDView.getTagValueCR();
                                    if (localTag == tag) {
                                        autoCompleteTextView12.setText(dateFormatter.format(newDate.getTime()));
                                        if (endDateAutoCompleteId == localTag) {
                                            resetButtonVisibility();
                                            Commons.endDate = dateFormatter.format(newDate.getTime());
                                            caliculateRates();

                                        } else {
                                            if (Commons.endDate == null || Commons.endDate.equalsIgnoreCase(""))
                                                Commons.startDate = dateFormatter.format(newDate.getTime());
                                            else
                                                setCommondatefordatefields(newDate);
                                        }
                                        finalMaptoSubmit.put(objCRDView.getNameCR(), dateFormatter.format(newDate.getTime()));
                                    }
                                }

                            }
                        }


                    }
                }, endDateAction, true, false);
    }

    private static void setCommondatefordatefields(Calendar newDate) {

        for (Map.Entry<Integer, AutoCompleteTextView> mapEntry : buttonAutoCompleteTextView.entrySet()) {
            int key = mapEntry.getKey();
            AutoCompleteTextView autoCompleteTextView12 = mapEntry.getValue();
//            autoCompleteTextView12.setText(dateFormatter.format(newDate.getTime()));
            for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                int localTag = objCRDView.getTagValueCR();
                if (localTag == key) {
                    autoCompleteTextView12.setText(dateFormatter.format(newDate.getTime()));
                    finalMaptoSubmit.put(objCRDView.getNameCR(), dateFormatter.format(newDate.getTime()));
                }
            }
        }
        resetButtonVisibility();
//        Commons.endDate = dateFormatter.format(newDate.getTime());
        Commons.startDate = dateFormatter.format(newDate.getTime());
        caliculateRates();
    }


    /*-------Method used for creating the random value between number we can change as well by changing the values--------*/
    public static int randomGenerateMethod(int low, int high) {

        Random r = new Random();
        int Low = low;
        int High = high;
        int Result = r.nextInt(High - Low) + Low;
        return Result;
    }

    static View.OnClickListener topButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int no_months = 0;
            if (view.getId() == R.id.moths3) {
                no_months = 3;
                months_3.setSelected(true);
                months_3.setTextColor(Color.WHITE);
                months_6.setSelected(false);
                months_6.setTextColor(Color.BLACK);
                months_9.setSelected(false);
                months_9.setTextColor(Color.BLACK);
                months_12.setSelected(false);
                months_12.setTextColor(Color.BLACK);
            } else if (view.getId() == R.id.moths6) {
                no_months = 6;
                months_6.setSelected(true);
                months_6.setTextColor(Color.WHITE);
                months_3.setSelected(false);
                months_3.setTextColor(Color.BLACK);
                months_9.setSelected(false);
                months_9.setTextColor(Color.BLACK);
                months_12.setSelected(false);
                months_12.setTextColor(Color.BLACK);
            } else if (view.getId() == R.id.moths9) {
                no_months = 9;
                months_9.setSelected(true);
                months_9.setTextColor(Color.WHITE);
                months_3.setSelected(false);
                months_3.setTextColor(Color.BLACK);
                months_6.setSelected(false);
                months_6.setTextColor(Color.BLACK);
                months_12.setSelected(false);
                months_12.setTextColor(Color.BLACK);


            } else if (view.getId() == R.id.moths12) {
                no_months = 12;
                months_12.setSelected(true);
                months_12.setTextColor(Color.WHITE);
                months_9.setSelected(false);
                months_9.setTextColor(Color.BLACK);
                months_3.setSelected(false);
                months_3.setTextColor(Color.BLACK);
                months_6.setSelected(false);
                months_6.setTextColor(Color.BLACK);


            }


            String value = startDate;
            date.set(Integer.parseInt(value.split("/")[2]), Integer.parseInt(value.split("/")[0]) - 1, Integer.parseInt(value.split("/")[1]));


            date.add(Calendar.MONTH, no_months);
            date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(value.split("/")[1]));
            for (Map.Entry<Integer, AutoCompleteTextView> mapEntry : buttonAutoCompleteTextView.entrySet()) {
                int key = mapEntry.getKey();
                if (endDateAutoCompleteId == key) {
                    AutoCompleteTextView autoCompleteTextView12 = mapEntry.getValue();

                    for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
                        int localTag = objCRDView.getTagValueCR();
                        if (localTag == endDateAutoCompleteId) {
                            autoCompleteTextView12.setText(dateFormatter.format(date.getTime()));
                            finalMaptoSubmit.put(objCRDView.getNameCR(), dateFormatter.format(date.getTime()));
                        }
                    }
                }
                Commons.endDate = dateFormatter.format(date.getTime());
            }
            //Need to caliculate pay and bill rate according to the given input in enddate.

            caliculateRates();


        }


    };

    public static void caliculateRates() {
        try {

            if (viewCreated) {

                if (billrate_et1 == null)
                    loadpayrate_dependencyedittext();
                else if (Commons.billRateISEnable && billrate_et1 != null)
                    createRequirementPage.loadBillRate(0, nContext, billrate_et1, billrate_et2, getTotalEstimatevalue());
                else
                    createRequirementPage.loadPayratTotalcontract(nContext, billrate_et1, billrate_et2, getTotalEstimatevalue());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAllValues() {

        if (billrate_et1.getText().length() > 0) {
            if (Commons.billRateISEnable)
                Commons.billRateLow = Float.parseFloat(billrate_et1.getText().toString());
            else
                Commons.payRateLow = Float.parseFloat(billrate_et1.getText().toString());
        } else {
            Commons.payRateLow = 0;
            Commons.billRateLow = 0;
        }
        if (billrate_et2.getText().length() > 0) {
            if (Commons.billRateISEnable)
                Commons.billRateHigh = Float.parseFloat(billrate_et2.getText().toString());
            else
                Commons.payRateHigh = Float.parseFloat(billrate_et2.getText().toString());
        } else {
            Commons.payRateHigh = 0;
            Commons.billRateHigh = 0;
        }


        if (getTotalEstimatevalue().getText().length() > 0)
            Commons.estimatedContractValue = Float.parseFloat(getTotalEstimatevalue().getText().toString());
        else
            Commons.estimatedContractValue = 0;


    }

    public static int getValue(String value) {
        int result = 0;
        if (value.equalsIgnoreCase("Yes")) {
            result = 1;
        }
        return result;

    }


    /*************************
     * method to create the json response into an array
     *******************/
    public static void methodToAddResponseToArray(JSONObject jsonObject, int randomId) {
        try {
            CreateRquirementDynamicViewObjects objCR = new CreateRquirementDynamicViewObjects();
            objCR.setNameCR(jsonObject.getString("name"));
            objCR.setValueCR(jsonObject.getString("value"));
            objCR.setIsDiaplayCR(jsonObject.getString("isDisplay"));
            objCR.setControltypeCR(jsonObject.getString("controlType"));
            objCR.setIsMandatoryCR(jsonObject.getString("isMandotary"));
            objCR.setIsReadOnlyCR(jsonObject.getString("isReadOnly"));
            objCR.setDefaultValueCR(jsonObject.getString("DefaultValues"));
            objCR.setActionNameCR(jsonObject.getString("ActionName"));
            objCR.setTagValueCR(randomId);
            dynamicViewObjectsArrayList.add(objCR);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomEditText getTotalEstimatevalue() {
        for (CreateRquirementDynamicViewObjects objCRDView : dynamicViewObjectsArrayList) {
            String key = objCRDView.getNameCR();
            int tag = objCRDView.getTagValueCR();

            if (key.equalsIgnoreCase("estContractValue"))
                return editetextboxHashmap.get(objCRDView.getTagValueCR());

        }
        return null;
    }

    public static class CustomDropdownModelAdapter extends ArrayAdapter<DropdownReasons> {

        private Context mContext;

        // Keeping the currently selected item
        int mCurrSelected = -1;

        // Since most of the actions gets the id but needs the position,
        // we'll map Ids to Positions
        private HashMap mIdToPosition;

        public CustomDropdownModelAdapter(@NonNull Context context) {
            super(context, 0);
            mContext = context;
            init();
        }

        private void init() {
            mIdToPosition = new HashMap();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if (listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.detailspage_item, parent, false);
            TextView tvTitle = (TextView) listItem.findViewById(R.id.details_thiredline);
            LinearLayout linearMain = (LinearLayout) listItem.findViewById(R.id.linearMain);
            listItem.findViewById(R.id.details_firsline_date).setVisibility(View.GONE);
            listItem.findViewById(R.id.details_firstline).setVisibility(View.GONE);
            listItem.findViewById(R.id.details_secondline).setVisibility(View.GONE);
            listItem.findViewById(R.id.details_fourthline).setVisibility(View.GONE);
            DropdownReasons dropdownReason = getItem(position);
            tvTitle.setText(dropdownReason.getText());

            if (dropdownReason.Selected) {
                linearMain.setBackgroundColor(mContext.getResources().getColor(R.color.red_text_bg));
            } else {
                linearMain.setBackgroundColor(Color.TRANSPARENT);
            }

            listItem.setTag(dropdownReason);
            return listItem;
        }

        public void add(DropdownReasons object, long id) {
            // Adding the new item to the Id-&gt;Position HashMap
            mIdToPosition.put(id, getCount());
            super.add(object);
        }

        @Override
        public void remove(@android.support.annotation.Nullable DropdownReasons object) {
            super.remove(object);
            mIdToPosition.remove(object.idText);
        }

        /**
         * Setting the item in the argumented position - as selected.
         *
         * @param position
         * @return
         */
        public long setSelectable(int position) {
            // The -1 value means that no item is selected
            if (mCurrSelected != -1) {
                getItem(mCurrSelected).Selected = false;
            }

            // Selecting the item in the position we got as an argument
            if (position != -1) {
                getItem(position).Selected = true;
                mCurrSelected = position;
            }

            // Making the list redraw
            notifyDataSetChanged();

            return getSelectedId();
        }

        public long setSelectableId(long id) {
            // First, we need to get the position for our item's id
            int pos = (int) mIdToPosition.get(id);
            return setSelectable(pos);
        }

        @Override
        public long getItemId(int position) {
            return super.getItem(position).idText;
        }

        /**
         * Needed to notify the ListView's system that the IDs we use here are unique to each item
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }

        public long getSelectedId() {
            if (mCurrSelected == -1)
                return -1;
            else {
                return getItemId(mCurrSelected);
            }
        }
    }

}

package com.example.medicalcenterapp.models;

import java.util.ArrayList;
import java.util.Arrays;

public class ResponsesModal {

    public String compareSymptoms(ArrayList<String> symptomsList) {

        int countCancer = cancerValues(symptomsList);
        int countObesity = obesityValues(symptomsList);
        int countHiv = hivValues(symptomsList);
        int countDiabetes = diabetesValues(symptomsList);
        int countTyphoid = typhoidValues(symptomsList);
        int countEpilepsy = epilepsyValues(symptomsList);
        int countKidney = kidneyValues(symptomsList);

        int[] weights = {countCancer, countDiabetes, countEpilepsy, countHiv, countKidney, countObesity, countTyphoid};
        int maxValue = getMaxValue(weights);

        if(countCancer == maxValue)
            return "Cancer";
        else if (countDiabetes == maxValue)
            return "Diabetes";
        else if (countEpilepsy == maxValue)
            return "Epilepsy";
        else if (countKidney == maxValue)
            return "Kidney";
        else if (countObesity == maxValue)
            return "Obesity";
        else if (countHiv == maxValue)
            return "HIV/AIDS";
        else if (countTyphoid == maxValue)
            return "Typhoid";
        return "No match";

    }

    private int kidneyValues(ArrayList<String> symptomsList){
        ArrayList<String> kidney = new ArrayList<>(Arrays.asList(SymptomsModal.KIDNEY));
        int countKidney = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (kidney.contains(symptomsList.get(i))) {
                countKidney++;
            }
        }
        return countKidney;
    }

    private int obesityValues(ArrayList<String> symptomsList){
        ArrayList<String> obesity = new ArrayList<>(Arrays.asList(SymptomsModal.OBESITY));
        int countObesity = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (obesity.contains(symptomsList.get(i))) {
                countObesity++;
            }
        }
        return countObesity;
    }

    private int typhoidValues(ArrayList<String> symptomsList){
        ArrayList<String> typhoid = new ArrayList<>(Arrays.asList(SymptomsModal.TYPHOID));
        int countTyphoid = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (typhoid.contains(symptomsList.get(i))) {
                countTyphoid++;
            }
        }
        return countTyphoid;
    }

    private int cancerValues(ArrayList<String> symptomsList){
        ArrayList<String> cancer = new ArrayList<>(Arrays.asList(SymptomsModal.CANCER));
        int countCancer = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (cancer.contains(symptomsList.get(i))) {
                countCancer++;
            }
        }
        return countCancer;
    }

    private int hivValues(ArrayList<String> symptomsList){
        ArrayList<String> hiv = new ArrayList<>(Arrays.asList(SymptomsModal.HIV));
        int countHiv = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (hiv.contains(symptomsList.get(i))) {
                countHiv++;
            }
        }
        return countHiv;
    }

    private int epilepsyValues(ArrayList<String> symptomsList){
        ArrayList<String> epilepsy = new ArrayList<>(Arrays.asList(SymptomsModal.EPILEPSY));
        int countEpilepsy = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (epilepsy.contains(symptomsList.get(i))) {
                countEpilepsy++;
            }
        }
        return countEpilepsy;
    }

    private int diabetesValues(ArrayList<String> symptomsList){
        ArrayList<String> diabetes = new ArrayList<>(Arrays.asList(SymptomsModal.DIABETES));
        int countDiabetes = 0;
        for (int i = 0; i < symptomsList.size(); i++) {
            if (diabetes.contains(symptomsList.get(i))) {
                countDiabetes++;
            }
        }
        return countDiabetes;
    }


    private int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i= 1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }
}

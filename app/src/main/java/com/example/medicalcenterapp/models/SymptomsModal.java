package com.example.medicalcenterapp.models;

public class SymptomsModal {

    /* List of diseases to be done
       Epilepsy ->done
       HIV/AIDS -> done
       Obesity -> done
       Typhoid -> done
       Kidney disease -> done
       Cancer -> done
       Diabetes -> done
       Blindness/ophthalmology

   */
    public static final String[] CANCER = {
            "lump", "abnormal bleeding", "prolonged cough", "weight loss", "decreased weight",
            "change in bowel movement", "headache", "seizures", "vertigo", "hemoptysis", "Dysphea",
            "lymhadenopathy", "hepatomegaly", "jaundice", "body pain", "fractures", "pain in the spine",
    };

    public static final String[] OBESITY = {
            "pain", "catatonia", "snore", "chest pain", "wave feature",
            "shortness of breath", "fatigue", "tired", "overweight", "systolic murmur",
            "mood depression", "ecchymosis", "out of breath", "sedentary", "angina pectoris",
            "cough", "unhappy", "labored breathing", "hypothermia", "dyspnea", "hematocrit decrease",
            "wheezing", "hypoxemia", "renal angel tenderness", "feverish", "feels hot",
    };

    public static final String[] EPILEPSY = {
            "seizure", "hypometabolism", "aura", "muscle twitch", "drowsiness",
            "tremor", "unresponsiveness", "hemiplegia", "myoclonus", "gurgle",
            "sleepy", "lethargy", "wheelchair bound", "lip smacking", "phonophobia",
            "rolling eyes", "hirsutism", "moody", "unresponsive", "headache", "ambidexterity",
            "absences finding", "spasm", "decreased body weight", "weight loss", "tumor cell invasion"
    };

    public static final String[] DIABETES = {
            "polyuria", "polydypsia", "shortness of breath", "chest pain", "asthenia", "nausea",
            "orthopnea", "rale", "sweating", "unresponsive", "mental status change", "vertigo",
            "vomiting", "labored breathing", "feeling suicidal", "abdominal pain", "milky eyes",
            "feeling strange", "gurgle", "nervousness", "abdominal tenderness", "regurgitates after swallowing",
            "version blurred", "urinary hesitation", "diarrhea", "seizure", "aura",
    };

    public static final String[] HIV = {
            "fever", "night sweat", "spontaneous rupture of membranes", "cough", "weight loss", "decreased body weight",
            "chill", "diarrhea", "pleuritic pain", "tachypnea", "productive cough", "muscle hypotonia",
            "hypotonic", "feeling suicidal",
    };

    public static final String[] TYPHOID = {
            "tiredness", "slow movement", "muscle cramps", "slow heart rate", "difficulty sleeping",
            "sensitivity to cold temperatures", "constipation"
    };

    public static final String[] KIDNEY = {
            "shortness of breath", "hyperkalemia", "chest pain", "fever", "bleeding vagina", "vomiting",
            "orthopnea", "hyperkalemia", "oliguria", "jugular venous distention", "nausea", "shortness of breath",
            "mental status change", "diarrhea", "asthenia", "chest tightness", "malaise", "chill", "rale",
            "fever", "pleutritic pain", "apyrexial", "gauaiac positive", "swelling", "catatonia", "unresponsiveness",
            "yelloe sputum", "hypotension", "hoypcalcemia result", "oliguria", "hemodynamically stable",
            "hypothermia", "heamorrhage", "prostatism", "bedridden", "fatigue"
    };

    private String symptomTitle;

    public SymptomsModal(String symptomTitle) {
        this.symptomTitle = symptomTitle;
    }


    public String getSymptomTitle() {
        return symptomTitle;
    }


}

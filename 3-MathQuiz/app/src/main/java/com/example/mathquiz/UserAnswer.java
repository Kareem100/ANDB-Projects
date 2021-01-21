package com.example.mathquiz;

import java.util.ArrayList;

public class UserAnswer {

    private ArrayList<String> freeTxtAns;
    private ArrayList<String> mcqAns;
    private ArrayList<ArrayList<String>> maqAns;
    private ArrayList<String> imgAns;

    public ArrayList<String> getFreeTxtAns() {
        return freeTxtAns;
    }

    public void setFreeTxtAns(ArrayList<String> freeTxtAns) {
        this.freeTxtAns = freeTxtAns;
    }

    public ArrayList<String> getMcqAns() {
        return mcqAns;
    }

    public void setMcqAns(ArrayList<String> mcqAns) {
        this.mcqAns = mcqAns;
    }

    public ArrayList<ArrayList<String>> getMaqAns() {
        return maqAns;
    }

    public void setMaqAns(ArrayList<ArrayList<String>> maqAns) {
        this.maqAns = maqAns;
    }

    public ArrayList<String> getImgAns() {
        return imgAns;
    }

    public void setImgAns(ArrayList<String> imgAns) {
        this.imgAns = imgAns;
    }
}

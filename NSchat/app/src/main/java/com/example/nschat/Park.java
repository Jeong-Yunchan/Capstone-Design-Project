package com.example.nschat;

import java.io.Serializable;

/**
 * Park getter setter 함수 선언
 */

public class Park implements Serializable {
    String number; // 관리번호
    String pname; // 공원명
    String padr; //소재지지번주소
    String pkind; //공원 종류
    String psize; //공원 면적
    String lat; // 위도
    String lng; //경도


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPadr() {
        return padr;
    }

    public void setPadr(String padr) {
        this.padr = padr;
    }

    public String getPkind() {
        return pkind;
    }

    public void setPkind(String pkind) {
        this.pkind = pkind;
    }

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }





    public String findIndex(String name) {
        if(this.pname.equals(name)) {
            return number;
        }
        else return null;
    }

}

package com.example.daz.latihan4;

/**
 * Created by daz on 3/24/2016.
 */
public class DATA {

    public static final String TABLE = "tdatasurat";

    // Labels Table Columns names
    public static final String KEY_nosurat = "no_surat";
    public static final String KEY_perihal = "perihal";
    public static final String KEY_keterangan = "keterangan";
    public static final String KEY_statussurat = "status_surat";
    public static final String KEY_tag = "tag";
    public static final String KEY_tglmasuk = "tglmasuk";
    public static final String KEY_tglkeluar = "tglkeluar";
    public static final String KEY_asal = "asal";

    private String no_surat;
    private String perihal;
    private String keterangan;
    private String status_surat;
    private String tag;
    private String tglmasuk;
    private String tglkeluar;
    private String asal;


    public String getNo_surat() {
        return no_surat;
    }

    public void setNo_surat(String no_surat) {
        this.no_surat = no_surat;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus_surat() {
        return status_surat;
    }

    public void setStatus_surat(String status_surat) {
        this.status_surat = status_surat;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTglmasuk() {
        return tglmasuk;
    }

    public void setTglmasuk(String tglmasuk) {
        this.tglmasuk = tglmasuk;
    }

    public String getTglkeluar() {
        return tglkeluar;
    }

    public void setTglkeluar(String tglkeluar) {
        this.tglkeluar = tglkeluar;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

}

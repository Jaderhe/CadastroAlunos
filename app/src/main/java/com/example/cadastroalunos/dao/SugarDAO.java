package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.model.Disciplina;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SugarDAO {

    public static long salvar(SugarRecord sugarRecord) {
        try {
            return sugarRecord.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o objeto: " + ex.getMessage());
            return -1;
        }
    }

    public static SugarRecord buscar(int id, Class<? extends SugarRecord> clazz) {
        try {
            return SugarRecord.findById(clazz, id);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar o objeto: " + ex.getMessage());
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List retornaObjetos(String where, String[] whereArgs, String orderBy, Class<? extends SugarRecord> clazz) {
        List<? extends SugarRecord> list = new ArrayList<>();
        try {
            list = SugarRecord.find(clazz, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de objetos: " + ex.getMessage());
        }
        return list.stream().map(clazz::cast).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List retornaObjetos(Class<? extends SugarRecord> clazz) {
        return retornaObjetos("", new String[]{}, "", clazz);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List retornaObjetos(Class<? extends SugarRecord> clazz, String orderBy) {
        return retornaObjetos("", new String[]{}, orderBy, clazz);
    }

    public static boolean delete(SugarRecord sugarRecord) {
        try {
            return SugarRecord.delete(sugarRecord);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar o objeto: " + ex.getMessage());
            return false;
        }

    }
}

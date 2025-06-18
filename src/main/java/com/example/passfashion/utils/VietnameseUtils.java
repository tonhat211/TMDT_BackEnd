package com.example.passfashion.utils;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class VietnameseUtils {
    public static String removeVietnameseDiacritics(String input) {
        if (input == null) return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Loại bỏ các dấu tiếng Việt
        String withoutDiacritics = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        // Chuyển đ/Đ thành d/D
        return withoutDiacritics.replaceAll("đ", "d").replaceAll("Đ", "D");
    }

    public static void main(String[] args) {
        System.out.println(VietnameseUtils.removeVietnameseDiacritics("Tô minh nhật"));
    }
}


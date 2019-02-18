package com.dna.service.builder;


public class CharDataBuilder {

    private String[] dna;

    public static CharDataBuilder builder (){
        return new CharDataBuilder();
    }

    public CharDataBuilder dna(String[] dna) {
        this.dna = dna;
        return this;
    }

    public char[][] build() {
        char[][] chars = new char[dna.length][dna.length];
        for (int x = 0; x < dna.length; x++) {
            chars[x] = dna[x].toCharArray();
        }
        return chars;
    }
}

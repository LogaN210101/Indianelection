package com.example.indianelection;

public class VoterReset {
    public String name;
    public int flag;
    VoterReset(String n, int f)
    {
        flag=f;
        name=n;
    }
    VoterReset()
    {
        flag = 0;
        name="";
    }
}

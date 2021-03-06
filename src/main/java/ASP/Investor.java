package ASP;

import java.util.ArrayList;
import java.util.List;

public class Investor implements InvestorAPI{
    private List<AssetBackedSecurity> ABSinvestedIn;
    private double balance;
    private int id;
    private String password;

    public Investor(double balance, List<AssetBackedSecurity> ABSinvestedIn){
        this.balance = balance;
        this.ABSinvestedIn = ABSinvestedIn;
    }

    public Investor(){

    }

    public double getBalance() {
        return balance;
    }


    public List<AssetBackedSecurity> getABSinvestedIn() {
        return ABSinvestedIn;
    }

    public String listABS (SPV spv, double minRisk, double maxRisk ) throws IllegalArgumentException{

        List<AssetBackedSecurity> catalog = new ArrayList<>();

        if (spv.ABSList.size()==0)
            throw new IllegalArgumentException("This Spv has no ABS's at the moment.");

        minRisk = minRisk * .01;
        String stringToReturn = "";
        for (AssetBackedSecurity abs: spv.ABSList){
            if (abs.getRiskValue() >= minRisk && abs.getRiskValue() <= maxRisk ){
                stringToReturn += "ABS id: " + abs.getId() + ". Associated Risk Value: " + abs.getRiskValue()*100 + "\n";
                catalog.add(abs);
            }
        }
        return stringToReturn;
    }

    public void buyABS(int ABSid, SPV spv, int shares) throws insufficientSharesException {
        spv.sellABSToInvestor(this, ABSid, shares);
    }

    public void setABSInvestedIn(List<AssetBackedSecurity> ABSinvestedIn){
        this.ABSinvestedIn = ABSinvestedIn;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String viewMyABS(){
        if (ABSinvestedIn.size() == 0)
            System.out.println("You have not purchased any ABS's");
        String stringToReturn = "";
        for (int i = 0; i < ABSinvestedIn.size();i++ ){
            stringToReturn += "ABS id: " + ABSinvestedIn.get(i).getId() +"\n" + "Amount of shares owned: " + ABSinvestedIn.get(i).getInvestorMap().get(id) + "\n" + "Risk value: " + ABSinvestedIn.get(i).getRiskValue() +"\n" + "SPV ID: " + ABSinvestedIn.get(i).getSpvid()+"\n" +"-----------------------"+"\n" ;
        }
        return stringToReturn;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }


}

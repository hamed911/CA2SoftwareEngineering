/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.transactioncontrol;

/**
 *
 * @author Hamed Ara
 */
public class Response {
    public String transactionID;
    public String type;
    public String deposit;
    public Boolean isSuccess;
    public String message;

    public Response(String transactionID, String type, String deposit, Boolean isSuccess, String massage) {
        this.transactionID = transactionID;
        this.type = type;
        this.deposit = deposit;
        this.isSuccess = isSuccess;
        this.message = massage;
    }
    
}

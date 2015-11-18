/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.io.IOException;

/**
 *
 * @author Hamed Ara
 */
public interface ISync {
    public void sync()throws IOException;
    
}

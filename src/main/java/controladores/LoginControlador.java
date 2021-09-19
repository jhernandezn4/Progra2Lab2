/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.MdUsuario;

/**
 *
 * @author GAMERS
 */
public class LoginControlador {
    UsuariosControlador uc;
    public static void main(String[] args) {
        
    }
    public Integer Ingresar(String usuario,String password){
        Integer ingreso=0;
        uc = new UsuariosControlador();
        MdUsuario encontrado = uc.buscar(usuario);
        
        if(encontrado!=null){
            
            if(password.equals(encontrado.getPassword())){
                ingreso=1;
            }else{
            }
        }
        
        return ingreso;
    }
}

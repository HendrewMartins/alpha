package org.alpha.interfaces;

import java.util.List;

import javax.ws.rs.core.Response;

import org.alpha.dto.UserLogin;
import org.alpha.entities.User;
import org.alpha.exeption.notFoundMessageExeption;

public interface UserInterface {
    //
    //*Save
    User saveUser(User user);

    //*Udate */
    User updateUser(Long id,User user) throws notFoundMessageExeption; 
        //it throws the exception in case of user not found

    //*Delete */
    void deleteUser(Long id) throws notFoundMessageExeption;

    //*All results are returned*/
    List<User> allUsers(); //I dont need to throw anything here cause it will return me an empty list

    //*Just one result is returned and it is surcherd by ID */
    User getUserById(Long id) throws notFoundMessageExeption;

    //*It searches for user name */
    List<User> findUserByName(String name) throws notFoundMessageExeption;

    //* It will return me the amount */
    Long countUser();

    ///*List user using pagination wich is a limited number of results */
    List<User> AllUserPagination(int pag, int quant);

    Response generateToken(UserLogin userLogin) throws Exception;

    boolean userExists(String user_name) throws Exception;
    
}

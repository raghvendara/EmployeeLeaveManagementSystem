package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;

import com.alacriti.elm.bo.LoginBo;
import com.alacriti.elm.resteasy.modelClasses.ForgotPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.NewPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.ResponseToForgotPassword;
import com.alacriti.elm.resteasy.modelClasses.ResponseToLoginPost;
import com.alacriti.elm.resteasy.modelClasses.UserLoginInfo;



public class DeligateLogin extends BaseDeligate{
	
	public ResponseToLoginPost deligateLoginValidate(UserLoginInfo userLoginInfo)
	{
		
		ResponseToLoginPost responseToPost=null;
	
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			LoginBo sampleBO = new LoginBo(connection);
			responseToPost = sampleBO.loginBo(userLoginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return responseToPost;
		
	}

	public ResponseToForgotPassword deligateforgotPassword(
			ForgotPasswordInfo forgotPasswordInfo) {
		ResponseToForgotPassword responseToForgotPassword=null;
		
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			LoginBo sampleBO = new LoginBo(connection);
			responseToForgotPassword = sampleBO.forgotPasswordBo(forgotPasswordInfo);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return responseToForgotPassword;
		
	}

	public boolean deligateResetPassword(NewPasswordInfo newPasswordInfo) {
		boolean flag=false;
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			LoginBo sampleBO = new LoginBo(connection);
			flag= sampleBO.resetPasswordBo(newPasswordInfo);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return flag;
	}
	
	
}
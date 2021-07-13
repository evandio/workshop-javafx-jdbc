/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.db.DbException;
import main.gui.util.Alerts;
import main.gui.util.Constraints;
import main.gui.util.Utils;
import main.model.entities.Department;
import main.model.services.DepartmentService;
import main.gui.listener.DataChangeListener;
import main.model.exception.ValidationException;

/**
 * FXML Controller class
 *
 * @author evand
 */
public class DepartmentFormController implements Initializable {
    
    private Department entity;
    private DepartmentService service;
    private List<DataChangeListener> dataChangeListener = new ArrayList<>();
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Label labelErroName;
    @FXML
    private Button btSave;
    
    private Button btCancel;
    
    public void onBtSaveAction(ActionEvent event) {
        
        if (entity == null) {
            throw new DbException("Entity was null!");
        }
        
        if (service == null) {
            throw new DbException("Service was null");
        }
        
        try {
            entity = getFormData();
            service.saveOrUpdatet(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        } catch (ValidationException e) {
            setErrorMensages(e.getErros());
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }
    
    public void setDepartment(Department entity) {
        this.entity = entity;
    }
    
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }
    
    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListener.add(listener);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }
    
    private void initializeNodes() {
        Constraints.setTextFieldDouble(txtId);
        Constraints.setTextFieldMaxLenght(txtName, 40);
    }
    
    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        //txtId.setText(String.valueOf(entity.getId()));
        txtId.setText(String.valueOf(entity.getId()).toString());
        txtName.setText(entity.getName());
    }
    
    private Department getFormData() {
        Department obj = new Department();
        
        ValidationException exception = new ValidationException("Validation error!");
        
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        
        if (txtName.getText() == null || txtName.getText().equals("")) {
            exception.addErros("name", "Field can't be empty!");
        }
        obj.setName(txtName.getText());
        
        if (exception.getErros().size() > 0) {
            throw exception;
        }
        
        return obj;
    }
    
    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListener) {
            listener.onDataChanged();
        }
    }
    
    private void setErrorMensages(Map<String, String> erros) {
        Set<String> fields = erros.keySet();
        if (fields.contains("name")) {
            labelErroName.setText(erros.get("name"));
        }
    }
}

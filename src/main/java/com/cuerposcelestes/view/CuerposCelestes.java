package com.cuerposcelestes.view;

import com.cuerposcelestes.controller.EstrellaController;
import com.cuerposcelestes.controller.PlanetaController;
import com.cuerposcelestes.controller.SateliteController;
import com.cuerposcelestes.entity.Estrella;
import com.cuerposcelestes.entity.Planeta;
import com.cuerposcelestes.entity.Satelite;
import com.cuerposcelestes.repository.EstrellaRepository;
import com.cuerposcelestes.repository.PlanetaRepository;
import com.cuerposcelestes.repository.SateliteRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class CuerposCelestes{
public static class CuerposCelestesView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtField1, txtField2, txtField3, txtField4, txtField5;
    private JLabel lblField1, lblField2, lblField3, lblField4, lblField5;
    private JComboBox<String> comboTipo;
    private JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;
    private EstrellaController estrellaController;
    private PlanetaController planetaController;
    private SateliteController sateliteController;
    private Integer selectedId = null;

    public CuerposCelestesView() {
        estrellaController = new EstrellaController(new EstrellaRepository());
        planetaController = new PlanetaController(new PlanetaRepository());
        sateliteController = new SateliteController(new SateliteRepository());
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Cuerpos Celestes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tipo de Cuerpo Celeste:"), gbc);
        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Estrella", "Planeta", "Satelite"});
        formPanel.add(comboTipo, gbc);


        lblField1 = new JLabel();
        lblField2 = new JLabel();
        lblField3 = new JLabel();


        txtField1 = new JTextField(20);
        txtField2 = new JTextField(20);
        txtField3 = new JTextField(20);



        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblField1, gbc);
        gbc.gridx = 1;
        formPanel.add(txtField1, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblField2, gbc);
        gbc.gridx = 1;
        formPanel.add(txtField2, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(lblField3, gbc);
        gbc.gridx = 1;
        formPanel.add(txtField3, gbc);




        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);


        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        updateFormForSelectedType((String) comboTipo.getSelectedItem());
        setupEventListeners();
    }

    private void updateFormForSelectedType(String selectedType) {

        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        switch (selectedType) {
            case "Estrella":
                setFieldVisibility(
                        "Nombre", true,
                        "Tipo", true,
                        "Edad", true

                );
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Tipo", "Edad"});
                break;

            case "Planeta":
                setFieldVisibility(
                        "Nombre", true,
                        "Masa", true,
                        "Distancia al Sol", true

                );
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Masa", "Distancia al Sol"});
                break;

            case "Satelite":
                setFieldVisibility(
                        "Nombre", true,
                        "Tamaño", true,
                        "Orbita", true

                );
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Tamaño", "Orbita"});
                break;
        }
        loadData();
        clearForm();
    }

    private void setFieldVisibility(String label1, boolean show1,
                                    String label2, boolean show2,
                                    String label3, boolean show3
 ) {
        lblField1.setText(label1);
        lblField2.setText(label2);
        lblField3.setText(label3);


        lblField1.setVisible(show1);
        txtField1.setVisible(show1);
        lblField2.setVisible(show2);
        txtField2.setVisible(show2);
        lblField3.setVisible(show3);
        txtField3.setVisible(show3);

    }

    private void loadData() {
        tableModel.setRowCount(0);
        String selectedType = (String) comboTipo.getSelectedItem();

        switch (selectedType) {
            case "Estrella":
                List<Estrella> estrellas = estrellaController.getAllEstrella();
                for (Estrella estrella : estrellas) {
                    tableModel.addRow(new Object[]{
                            estrella.getId(),
                            estrella.getNombre(),
                            estrella.getTipo(),
                            estrella.getEdad(),

                    });
                }
                break;

            case "Planeta":
                List<Planeta> planetas = planetaController.getAllPlaneta();
                for (Planeta planeta : planetas) {
                    tableModel.addRow(new Object[]{
                            planeta.getId(),
                            planeta.getNombre(),
                            planeta.getMasa(),
                            planeta.getDistanciaSol(),

                    });
                }
                break;

            case "Satelite":
                List<Satelite> satelites = sateliteController.getAllSatelite();
                for (Satelite satelite : satelites) {
                    tableModel.addRow(new Object[]{
                            satelite.getId(),
                            satelite.getNombre(),
                            satelite.getTamano(),
                            satelite.getOrbita(),

                    });

                }
                break;
        }
    }


    private void setupEventListeners() {
        comboTipo.addActionListener(e -> {
            String selectedType = (String) comboTipo.getSelectedItem();
            updateFormForSelectedType(selectedType);
        });

        btnGuardar.addActionListener(e -> {
            String selectedType = (String) comboTipo.getSelectedItem();
            try {
                switch (selectedType) {
                    case "Estrella":
                        Estrella estrella = new Estrella();
                        estrella.setNombre(txtField1.getText());
                        estrella.setTipo(txtField2.getText());
                        estrella.setEdad(Float.parseFloat(txtField3.getText()));


                        if (selectedId != null) {
                            estrella.setId(selectedId);
                            estrellaController.updateEstrella(estrella);
                        } else {
                            estrellaController.saveEstrella(estrella);
                        }
                        break;

                    case "Planeta":
                        Planeta planeta = new Planeta();
                        planeta.setNombre(txtField1.getText());
                        planeta.setMasa(Float.parseFloat(txtField2.getText()));
                        planeta.setDistanciaSol(Float.parseFloat(txtField3.getText()));

                        if (selectedId != null) {
                            planeta.setId(selectedId);
                            planetaController.updatePlaneta(planeta);
                        } else {
                            planetaController.savePlaneta(planeta);
                        }
                        break;

                    case "Satelite":
                        Satelite satelite = new Satelite();
                        satelite.setNombre(txtField1.getText());
                        satelite.setTamano(Float.parseFloat(txtField2.getText()));
                        satelite.setOrbita(txtField3.getText());

                        if (selectedId != null) {
                            satelite.setId(selectedId);
                            sateliteController.updateSatelite(satelite);
                        } else {
                            sateliteController.saveSatelite(satelite);
                        }
                        break;
                }

                loadData();
                clearForm();
                JOptionPane.showMessageDialog(this, "Guardado exitosamente");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                selectedId = (Integer) table.getValueAt(selectedRow, 0);
                String selectedType = (String) comboTipo.getSelectedItem();

                switch (selectedType) {
                    case "Estrella":
                        txtField1.setText((String) table.getValueAt(selectedRow, 1));
                        txtField2.setText((String) table.getValueAt(selectedRow, 2));
                        txtField3.setText(table.getValueAt(selectedRow, 3).toString());
                        break;

                    case "Planeta":
                        txtField1.setText((String) table.getValueAt(selectedRow, 1));
                        txtField2.setText(table.getValueAt(selectedRow, 2).toString());
                        txtField3.setText(table.getValueAt(selectedRow, 3).toString());

                        break;

                    case "Satelite":
                        txtField1.setText((String) table.getValueAt(selectedRow, 1));
                        txtField2.setText((String) table.getValueAt(selectedRow, 2));
                        txtField3.setText(table.getValueAt(selectedRow, 3).toString());

                        break;
                }
                btnGuardar.setText("Actualizar");
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Integer id = (Integer) table.getValueAt(selectedRow, 0);
                String selectedType = (String) comboTipo.getSelectedItem();

                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de eliminar este registro?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        switch (selectedType) {
                            case "Estrella":
                                estrellaController.deleteEstrella(id);
                                break;
                            case "Planeta":
                                planetaController.deletePlaneta(id);
                                break;
                            case "Satelite":
                                sateliteController.deleteSatelite(id);
                                break;
                        }
                        loadData();
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Eliminado exitosamente");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                "Error al eliminar: " + ex.getMessage());
                    }
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean rowSelected = table.getSelectedRow() >= 0;
                btnEditar.setEnabled(rowSelected);
                btnEliminar.setEnabled(rowSelected);
            }
        });

        btnLimpiar.addActionListener(e -> {
            clearForm();
            btnGuardar.setText("Guardar");
        });
    }

    private void clearForm() {
        txtField1.setText("");
        txtField2.setText("");
        txtField3.setText("");

        selectedId = null;
        table.clearSelection();
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setText("Guardar");
    }
    }
}


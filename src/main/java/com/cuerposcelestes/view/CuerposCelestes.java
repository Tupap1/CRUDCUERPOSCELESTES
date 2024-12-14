package com.cuerposcelestes.view;

import com.cuerposcelestes.controller.EstrellaController;
import com.cuerposcelestes.entity.Estrella;
import com.cuerposcelestes.repository.EstrellaRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CuerposCelestes {
    public static class CuerposCelestesView extends JFrame {
        private JTable table;
        private DefaultTableModel tableModel;
        private JTextField txtNombre, txtApellido, txtEdad;
        private JComboBox<String> comboTipo;
        private JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;
        private EstrellaController controller;
        private Integer selectedId = null;

        public CuerposCelestesView() {
            controller = new EstrellaController(new EstrellaRepository()); 
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
            gbc.gridx = 1; gbc.gridy = 0;
            comboTipo = new JComboBox<>(new String[]{"Estrella", "Planeta", "Satelite"});
            formPanel.add(comboTipo, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            formPanel.add(new JLabel("Nombre:"), gbc);
            gbc.gridx = 1; gbc.gridy = 1;
            txtNombre = new JTextField(20);
            formPanel.add(txtNombre, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2;
            formPanel.add(new JLabel("Tipo:"), gbc);
            gbc.gridx = 1; gbc.gridy = 2;
            txtApellido = new JTextField(20);
            formPanel.add(txtApellido, gbc);
            
            gbc.gridx = 0; gbc.gridy = 3;
            formPanel.add(new JLabel("Edad:"), gbc);
            gbc.gridx = 1; gbc.gridy = 3;
            txtEdad = new JTextField(20);
            formPanel.add(txtEdad, gbc);
            


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            btnGuardar = new JButton("Guardar");
            btnEditar = new JButton("Editar");
            btnEliminar = new JButton("Eliminar");
            btnLimpiar = new JButton("Limpiar");

            buttonPanel.add(btnGuardar);
            buttonPanel.add(btnEditar);
            buttonPanel.add(btnEliminar);
            buttonPanel.add(btnLimpiar);

            String[] columnNames = {"ID", "Nombre", "Apellido", "Edad"};
            tableModel = new DefaultTableModel(columnNames, 0) {
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
            if (selectedType.equals("Estrella")) {
                setFieldVisibility("Nombre", true, "Tipo", true, "Edad", true, null, false, null, false);
            } else if (selectedType.equals("Planeta")) {
                setFieldVisibility("Nombre", true, "Masa", true, "Distancia al Sol", true, null, false, null, false);
            } else if (selectedType.equals("Satelite")) {
                setFieldVisibility("Nombre", true, "Tamaño", true, "Órbita", true, null, false, null, false);
            }
        
            revalidate();
            repaint();
        }
        

        private void setupEventListeners() {
            comboTipo.addActionListener(e -> {
                String selectedType = (String) comboTipo.getSelectedItem();
                updateFormForSelectedType(selectedType);
                clearForm(); // Clear the form when switching types
            });


            btnGuardar.addActionListener(e -> {
                String selectedType = (String) comboTipo.getSelectedItem();
                try {
                    if (selectedType.equals("Estrella")) {
                        Estrella estrella = new Estrella();
                        estrella.setNombre(txtNombre.getText());
                        estrella.setTipo(txtApellido.getText());
                        estrella.setEdad(Float.parseFloat(txtEdad.getText()));
            
                        if (selectedId != null) {
                            estrella.setId(selectedId);
                            controller.updateEstrella(estrella);
                        } else {
                            controller.saveEstrella(estrella);
                        }
                    } else if (selectedType.equals("Planeta")) {
                        // Planeta-specific save logic here
                    } else if (selectedType.equals("Satelite")) {
                        // Satelite-specific save logic here
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
                    txtNombre.setText((String) table.getValueAt(selectedRow, 1));
                    txtApellido.setText((String) table.getValueAt(selectedRow, 2));
                    txtEdad.setText(table.getValueAt(selectedRow, 3).toString());
                }
            });

            btnEliminar.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Integer id = (Integer) table.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro de eliminar este registro?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            controller.deleteEstrella(id);
                            loadData();
                            JOptionPane.showMessageDialog(this, "Eliminado exitosamente");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
                        }
                    }
                }
            });

            btnLimpiar.addActionListener(e -> clearForm());

            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    btnEditar.setEnabled(table.getSelectedRow() >= 0);
                    btnEliminar.setEnabled(table.getSelectedRow() >= 0);
                }
            });
        }

        private void loadData() {
            tableModel.setRowCount(0);

            List<Estrella> estrellas = controller.getAllEstrella();
            for (Estrella estrella : estrellas) {
                Object[] row = {
                        estrella.getId(),
                        estrella.getNombre(),
                        estrella.getTipo(),
                        estrella.getEdad()
                };
                tableModel.addRow(row);
            }
        }

        private void clearForm() {
            txtNombre.setText("");
            txtApellido.setText("");
            txtEdad.setText("");
            selectedId = null;
        
            if (comboTipo.getSelectedItem().equals("Planeta")) {
                // Clear Planeta-specific fields
                // Example: txtMasa.setText("");
            } else if (comboTipo.getSelectedItem().equals("Satelite")) {
                // Clear Satelite-specific fields
                // Example: txtTamaño.setText("");
            } else if (comboTipo.getSelectedItem().equals("Estrella")) {

            }

            table.clearSelection();
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
            private void setFieldVisibility(String nombreLabel, boolean showNombre, String field2Label, boolean showField2, String field3Label, boolean showField3, String field4Label, boolean showField4, String field5Label, boolean showField5) {
                // Logic to set label text dynamically and control visibility for each pair of label and input
            }
        }
    }

import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.sql.*; //needed to connect to SQL...

class DatabaseConnection
{
    private static Connection connection;

    public static void openDatabaseConnection()
    {
        String connectionString = "jdbc:sqlserver://localhost:1433;DatabaseName=Hospital_Management;IntegratedSecurity=true;trustServerCertificate=true;";
        try 
        {
            connection = DriverManager.getConnection(connectionString);
            System.out.println("\nDatabase connected Succesfully.\n");
        }
        catch (SQLException e)
        {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }

    public static void closeDatabaseConnection()
    {
        try
        {
            if (!connection.isClosed())
            {
                connection.close();
                System.out.println("Database closed properlly.");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error while trying to close the Database.");
            e.printStackTrace();
        }
    }
}

class Patient
{
    String fname, lname, gender;
    LocalDate dateofbirth=null;
    LocalDate checkin;
    int patientid;
    String doctor;
    String Medication;
    Scanner scanner = new Scanner(System.in);

    public void addNewPatient()
    {
        System.out.print("Enter first name: ");
        fname=scanner.nextLine();
        System.out.print("Enter last name: ");
        lname=scanner.nextLine();
        while (dateofbirth==null)
        {
            try
            {
                System.out.print("Enter date of birth(Year-Month-Date): ");
                String dob = scanner.nextLine();
                dateofbirth = LocalDate.parse(dob);
            }
            catch(DateTimeException e)
            {
                System.out.println("You entered an invalid date. Try again.");
            }
        }
        System.out.print("Enter Gender(M or F): ");
        gender = scanner.nextLine();

        String sql = "INSERT INTO Patient (firstname, lastname, dateofbirth, gender) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateofbirth));
            preparedStatement.setString(4, gender);
            int rowaffected = preparedStatement.executeUpdate();
            if (rowaffected>0)
            {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                    {
                        patientid = generatedKeys.getInt(1);
                    }
                    else
                    {
                        System.out.println("Patient ID not retrieved.");
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error while adding new patient to Database.");
            e.printStackTrace();
        }
    }

    public void displayPatient()
    {
        String searchfname, searchlname;
        System.out.print(" Enter patient first name: ");
        searchfname=scanner.nextLine();
        System.out.print(" Enter patient last name: ");
        searchlname=scanner.nextLine();

        String sql = "SELECT * FROM Patient WHERE firstname = ? AND lastname = ?";
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
        {
            preparedStatement.setString(1, searchfname);
            preparedStatement.setString(2, searchlname);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next())
            {
                patientid = resultSet.getInt("patientid");
                System.out.println("\nPatient History: ");
                System.out.printf(" Name: %s %s\n DOB: %s\n Gender: %s\n Last Check-in: %s\n",
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getDate("dateofbirth"),
                resultSet.getString("gender"),
                resultSet.getDate("lastcheckin"));
            }
            else
            {
                System.out.println("Patient is not found in the database.");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error while retrieving Patient's Data.");
            e.printStackTrace();
        }
    }

    public void updatePatient(int choice2)
    {
        String field, sql, updatedValue;

        if (choice2==1)
        {
            field = "firstname";
        }
        else if (choice2==2)
        {
            field = "lastname";
        }
        else if (choice2==3)
        {
            field = "dateofbirth";
        }
        else
        {
            field = "gender";
        }
        System.out.print("Enter the updated data: ");
        updatedValue = scanner.nextLine();
        sql = "UPDATE Patient SET " + field + " = ? WHERE patientid = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
        {
            preparedStatement.setString(1, updatedValue);
            preparedStatement.setInt(2, patientid);
            int affectedrows = preparedStatement.executeUpdate();
            if (affectedrows>0)
            {
                System.out.println("Updated succesfully.");
            }
            else
            {
                System.out.println("Update failed. Patient not found in the Database.");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error while updating Patient data.");
            e.printStackTrace();
        }
    }
}

class Doctor
{
    int docid;
    String fname, lname, specialization;
    Boolean status;
    Scanner scanner = new Scanner(System.in);

    public void addNewDoctor()
    {
        System.out.print("Enter first name: ");
        fname = scanner.nextLine();
        System.out.print("Enter last name: ");
        lname = scanner.nextLine();
        System.out.print("Enter speciality: ");
        specialization = scanner.nextLine();
        
        String sql = "INSERT INTO Doctor(firstname, lastname, specialty) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
        {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, specialization);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Error while adding new Doctor to Database.");
            e.printStackTrace();
        }
    }

    public void displayDoctor()
    {
        String sql = "SELECT * FROM Doctor";
        try(Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql))
        {
            System.out.println("Doctor information: ");
            System.out.println("");
            System.out.printf("%-10s %-15s %-15s %-20s %-10s\n", "DoctorID", "First Name", "Last Name", "Specialty", "Availability");
            //System.out.printf(" %-15s %-15s %-20s %-10s\n", "First Name", "Last Name", "Specialty", "Availability");
            while(resultSet.next())
            {
                System.out.printf("  %-10d %-15s %-15s %-20s %-10s\n",
                resultSet.getInt("doctorid"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("specialty"),
                resultSet.getBoolean("docstatus") ? "Yes" : "No");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error displaying doctor information.");
            e.printStackTrace();
        }
    }

    public void updateDoctor(int choice2)
    {
        String sql;
    
        if (choice2 >= 1 && choice2 <= 3) 
        {
            String field = "";
            if (choice2==1)
            {
                field = "firstname";
            }
            else if (choice2==2)
            {
                field = "lastname";
            }
            else
            {
                field = "speciality";
            }

            sql = "UPDATE Doctor SET " + field + " = ? WHERE doctorid = ?";
            System.out.print("Enter updated value: ");
            String updatedValue = scanner.nextLine();
            
            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql)) 
            {
                preparedStatement.setString(1, updatedValue);
                preparedStatement.setInt(2, docid);
            
                int rowsAffected = preparedStatement.executeUpdate();
            
                if (rowsAffected > 0) 
                {
                    System.out.println("Update successful.");
                } 
                else 
                {
                    System.out.println("Update failed. Doctor not found in the database.");
                }
            } 
            catch (SQLException e) 
            {
                System.out.println("Error occurred while updating doctor information.");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.print("Enter doctor status (1 for Available, 0 for Not Available): ");
            int status = scanner.nextInt();
            scanner.nextLine();
        
            if (status != 0 && status != 1) 
            {
                System.out.println("Invalid input. Please enter 1 or 0.");
                return;
            }
        
            boolean boolValue = status == 1;
        
            sql = "UPDATE Doctor SET docstatus = ? WHERE doctorid = ?";
        
            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql)) 
            {
                preparedStatement.setBoolean(1, boolValue);
                preparedStatement.setInt(2, docid);
        
                int rowsAffected = preparedStatement.executeUpdate();
        
                if (rowsAffected > 0) 
                {
                    System.out.println("Update successful.");
                } 
                else 
                {
                    System.out.println("Update failed. Doctor not found in the database.");
                }
            } 
            catch (SQLException e) 
            {
                System.out.println("Error occurred while updating doctor information.");
                e.printStackTrace();
            }
        }
    }
}

class Medicine
{
    String name, expdate;
    LocalDate expiry_date=null;
    //Boolean stock;
    int quantity, medid;
    Scanner scanner = new Scanner(System.in);

    public void addNewMedicine()
    {
        System.out.print("Enter Medicine Name: ");
        name = scanner.nextLine();
        while (expiry_date==null)
        {
            try 
            {
                System.out.print("Enter Expiry Date(YEAR-MONTH_DATE): ");
                expdate = scanner.nextLine();
                expiry_date=LocalDate.parse(expdate);
            }
            catch (DateTimeException e)
            {
                System.out.println("You entered an Invalid date. Try again.");
            }
        }
        System.out.print("Enter Quantity: ");
        quantity = scanner.nextInt();
        scanner.nextLine();

        String sql = "INSERT INTO Medicine (medname, expirydate, quantity) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, java.sql.Date.valueOf(expiry_date));
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Succesfully added to Database.");
        }
        catch (SQLException e)
        {
            System.out.println("Error while adding new Medicine to the Database.");
            e.printStackTrace();
        }
    }

    public void displayMedicine()
    {
        String sql = "SELECT * FROM Medicine";
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            System.out.println("Medicine Inventory: ");
            System.out.printf("%-10s %-20s %-15s %-10s\n", "Med ID", "Name", "Expiry Date", "Quantity");
            while (resultSet.next())
            {
                System.out.printf("%-10d %-20s %-15s %-10d\n", 
                resultSet.getInt("medid"),
                resultSet.getString("medname"), 
                resultSet.getDate("expirydate"),
                resultSet.getInt("quantity"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error while displaying Medicine Inventory.");
            e.printStackTrace();
        }
    }

    public void updateMedicine(int choice2)
    {
        String sql, updatedValue;
        int updatedQuantity;
        // LocalDate
        if (choice2==1) //med name
        {
            System.out.print("Enter updated data: ");
            updatedValue = scanner.nextLine();
            sql = "UPDATE Medicine SET medname = ? WHERE medid = ?";

            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
            {
                preparedStatement.setString(1, updatedValue);
                preparedStatement.setInt(2, medid);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected>0)
                {
                    System.out.println("Updated succesfully.");
                }
                else
                {
                    System.out.println("Update failed. Medicine not found in Database.");
                }
            }
            catch (SQLException e)
            {
                System.out.println("Error while updating Medicine Inventory.");
            }
        }
        else if (choice2==2) // expiry date
        {
            System.out.print("Enter updated data: ");
            updatedValue = scanner.nextLine();
            sql = "UPDATE Medicine SET expirydate = ? WHERE medid = ?";

            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
            {
                try
                {
                    LocalDate date = LocalDate.parse(updatedValue);
                    preparedStatement.setDate(1, java.sql.Date.valueOf(updatedValue));
                }
                catch (DateTimeException e)
                {
                    System.out.println("Invalid Date Format.");
                    return;
                }
                preparedStatement.setInt(2, medid);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected>0)
                {
                    System.out.println("Updated succesfully.");
                }
                else
                {
                    System.out.println("Update failed. Medicine not found in Database.");
                }
            }
            catch (SQLException e)
            {
                System.out.println("Error while updating Medicine Inventory.");
                e.printStackTrace();
            }
        }
        else // quantity
        {
            System.out.print("Enter upadted data: ");
            updatedQuantity = scanner.nextInt();
            scanner.nextLine();
            sql = "UPDATE Medicine SET quantity = ? WHERE medid = ?";

            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
            {
                preparedStatement.setInt(1, updatedQuantity);
                preparedStatement.setInt(2, medid);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected>0)
                {
                    System.out.println("Updated Succesfully.");
                }
                else
                {
                    System.out.println("Update failed. Medicine not found in Database.");
                }
            }
            catch (SQLException e)
            {
                System.out.println("Error while updating Medicine Inventory.");
                e.printStackTrace();
            }
        }
    }
}

class Appointment
{
    Patient patient = new Patient();
    Doctor doctor = new Doctor();
    String appointment_date, fname, lname;
    int docid, patientid;
    Scanner scanner = new Scanner(System.in);

    public void newAppointment(int choice2)
    {
        if (choice2==1)
        {
            patient.addNewPatient();
        }
        else if (choice2==2)
        {
            patient.displayPatient();
        }
        //patient.addNewPatient();
        doctor.displayDoctor();
        System.out.println("Choose the Doctor ID of the doctor you want: ");
        docid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter appointment date and time (Year-Month-Day Hour:Minute:Second): ");
        appointment_date = scanner.nextLine();

        String sql = "INSERT INTO Appointment(patientid, doctorid, appointmentdate) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql))
        {
            preparedStatement.setInt(1, patient.patientid);
            preparedStatement.setInt(2, docid);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(appointment_date));
            preparedStatement.executeUpdate();
            System.out.println("Appointment set succesfully.");
        }
        catch (SQLException e)
        {
            System.out.println("Error occured while trying to set appointment.");
            e.printStackTrace();
        }
    }

    public void existingAppointment()
    {
        System.out.print("Enter first name of Patient: ");
        fname = scanner.nextLine();
        System.out.print("Enter last name of Patient: ");
        lname = scanner.nextLine();

        String get_id_sql = "SELECT patientid FROM Patient where firstname = ? AND lastname = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(get_id_sql))
        {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                patientid = resultSet.getInt("patientid");

                String appt_sql = "SELECT * FROM Appointment WHERE patientid = ?";
                try(PreparedStatement appointmentStatement = DatabaseConnection.getConnection().prepareStatement(appt_sql))
                {
                    appointmentStatement.setInt(1, patientid);
                    ResultSet appointmResultSet = appointmentStatement.executeQuery();

                    System.out.println("Appointment for " + fname + " " + lname);
                    System.out.printf("%-15s %-15s %-20s\n", "Appointment ID", "Doctor ID", "Appointment Date");

                    boolean hasAppointment=false;
                    while (appointmResultSet.next())
                    {
                        hasAppointment=true;
                        System.out.printf("%-15d %-15d %-20s\n" , 
                        appointmResultSet.getInt("appointmentid"),
                        appointmResultSet.getInt("doctorid"),
                        appointmResultSet.getTimestamp("appointmentdate"));
                    }
                    if (hasAppointment==false)
                    {
                        System.out.println("No appointments found for " + fname + " " + lname);
                    }
                }
                catch (SQLException e)
                {
                    System.out.println("Error while retrieving appointments for patient.");
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Patient is not in the database.");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error while trying to finf patient in database");
            e.printStackTrace();
        }

    }
}

public class Hospital_Management
{
    public static void main(String args[])
    {
        DatabaseConnection.openDatabaseConnection();

        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Medicine medicine = new Medicine();
        Appointment appointment = new Appointment();

        Scanner scanner = new Scanner(System.in);
        int choice, choice2;
        boolean repeat=true;
        do
        {
            System.out.println("---------------------------------------------------------------------------\n\n1. Appointment setting\n2. Doctor's List\n3. Medicine Inventory\n4. Patient History\n5. Update Database\n6. Exit\n\n---------------------------------------------------------------------------");
            choice=scanner.nextInt();
            scanner.nextLine();
            if (choice>=1 && choice<=6)
            {
                switch (choice) 
                {
                    case 1: //Appointment Setting
                        System.out.println("I. Appointment Setting\n\t1. New appointment\n\t2. Existing appointment\n");
                        choice2 = scanner.nextInt();
                        if (choice2==1) // new appt.
                        {
                            System.out.println("  1. New Patient\n  2. Existing Patient");
                            choice2 = scanner.nextInt();
                            appointment.newAppointment(choice2);
                        }
                        else if (choice2==2) // existing appt.
                        {
                            appointment.existingAppointment();
                        }
                        else
                        {
                            System.out.println("Invalid number entered.");
                        }
                        break;
                    case 2: //Display Doctors
                        System.out.println("II. Doctor's List\n\t");
                        doctor.displayDoctor();
                        break;
                    case 3: // Medicine Inventory
                        System.out.println("III. Medicine Inventory\n\t");
                        medicine.displayMedicine();
                        break;
                    case 4: //Enter patient name and display Patient History
                        System.out.print("IV. Patient History\n");
                        patient.displayPatient();
                        break;
                    case 5: // Update
                        System.out.println("V. Update\n\t1. Update existing data\n\t2. Create new data");
                        choice2 = scanner.nextInt();
                        scanner.nextLine();
                        if (choice2==1) // update existing data
                        {
                            System.out.println("\t1. Patient History\n\t2. Doctor Information\n\t3. Medicine Inventory");
                            choice2 = scanner.nextInt();
                            scanner.nextLine();
                            if (choice2==1) // patient history (EXISTING)
                            {
                                System.out.println("Enter data of patient to be updated.");
                                patient.displayPatient();
                                System.out.println("\t\t1. First name\n\t\t2. Last name\n\t\t3. Date of birth\n\t\t4. Gender");
                                choice2 = scanner.nextInt();
                                scanner.nextLine();
                                if (choice2>=1 && choice2<=4)
                                {
                                    patient.updatePatient(choice2);
                                }
                                else
                                    System.out.println("Invalid number entered.");
                            }
                            else if (choice2==2) //doctor info (EXISTING)
                            {
                                doctor.displayDoctor();
                                System.out.print("Enter the Doctor ID to be updated: ");
                                doctor.docid = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("\t\t1. First name\n\t\t2. Last name\n\t\t3. Speciality\n\t\t4. Status");
                                choice2 = scanner.nextInt();
                                scanner.nextLine();
                                if (choice2>=1 && choice2<=4)
                                {
                                    doctor.updateDoctor(choice2);
                                }
                                else 
                                    System.out.println("Invalid Number Entered.");
                            }
                            else if (choice2==3) //medicine inventory (EXISTING)
                            {
                                medicine.displayMedicine();
                                System.out.print("Enter Medicine Id to update: ");
                                medicine.medid = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("\t\t1. Name\n\t\t2. Expiry Date\n\t\t3. Quantity");
                                choice2 = scanner.nextInt();
                                scanner.nextLine();
                                if (choice2>=1 && choice2<=3)
                                {
                                    medicine.updateMedicine(choice2);
                                }
                                else
                                    System.out.println("Invalid number entered.");
                            }
                            else
                                System.out.println("Invalid number entered.");
                        }
                        else if (choice2==2) // new data
                        {
                            System.out.println("\t1. Patient History\n\t2. Doctor Information\n\t3. Medicine Inventory");
                            choice2 = scanner.nextInt();
                            scanner.nextLine();
                            if(choice2==1) //patient history (NEW)
                            {
                                patient.addNewPatient();
                            }
                            else if(choice2==2)// Doctor info (NEW)
                            {
                                doctor.addNewDoctor();
                            }
                            else if(choice2==3) // Medicine inventory (NEW)
                            {
                                medicine.addNewMedicine();
                            }
                            else
                                System.out.println("Invalid number entered.");
                        }
                        else
                            System.out.println("Invalid number entered.");
                        break;
                    case 6: //Exit
                        DatabaseConnection.closeDatabaseConnection();
                        repeat=false;
                        break;
                }
            }
            else
                System.out.println("You entered an Invalid Number.");
        }
        while (repeat==true);
    }
}
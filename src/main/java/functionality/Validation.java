package functionality;

 public class Validation {
    private boolean isValid;
    private String ErrorMessage;

     /**
      * Validate user input and return boolean value that will be used to determine is the input valid or not
      */
    public Validation(String name, String price, String stock, String min, String max, String additionalInfo){

        /*Validate empty fields*/
        if(!name.equals("") && !additionalInfo.equals("")){

            /**
             * Validate type of data entered
             */
            try{
                Double.parseDouble(price);
                Integer.parseInt(stock);
                Integer.parseInt(min);
                Integer.parseInt(max);
                this.isValid = true;

                /**
                 * Min, Max Validation
                 */
                int minimum = Integer.parseInt(min);
                int invStock = Integer.parseInt(stock);
                int maximum = Integer.parseInt(max);
                if(minimum > maximum || invStock > maximum ||  invStock < minimum){
                    this.isValid = false;
                    this.ErrorMessage = "Wrong data in Min/Max or Inventory Fields";
                }
            }
            catch(NumberFormatException err){
                this.isValid = false;
                this.ErrorMessage = "Wrong data was entered in Numerical Fields.";
            }

        }

    else{
            this.isValid = false;
            this.ErrorMessage = "No empty fields are allowed!";
        }

    }

    /**
     * Return result of validation for future use
     */
    public boolean getValidationValue(){
        return this.isValid;
    }

    /*Return errors if any*/
     public String getErrorMessage(){
         return this.ErrorMessage;
     }

}

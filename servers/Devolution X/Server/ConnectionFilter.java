   import java.util.ArrayList;
        
         /**
         * Filters out fast and normal client connection speeds.
         * SYI/DoS Flooding Protection.
         * @author Gnarly
         * 
         */
        public class ConnectionFilter
        {
        
            private static ArrayList<String> HOST_LIST = new ArrayList<String>();
            private static ArrayList<String> CONNECTIONS = new ArrayList<String>();
        
            private int cycle;
            private boolean isFlooder;
            private boolean wasFlooder;
            private boolean wasConnected;
        
            public ConnectionFilter()
            {
                System.out.println("[ConnectionFilter] - Initialized");
            }
        
            /**
             * Setter for isFlooder boolean
             * @param isFlooder
             */
            private void setFlooder(boolean isFlooder)
            {
                this.isFlooder = isFlooder;
            }
        
            /**
             * Setter for wasFlooder boolean
             * @param wasFlooder
             */
            private void setWasFlooder(boolean wasFlooder)
            {
                this.wasFlooder = wasFlooder;
            }
            
            /**
             * Setter for wasConnected boolean
             * @param wasConnected
             */
            private void setWasConnected(boolean wasConnected)
            {
                this.wasConnected = wasConnected;
            }
            
            /**
             * @return isFlooder
             */
            private boolean isFlooder()
            {
                return isFlooder;
            }
        
            /**
             * @return isWasFlooder
             */
            private boolean isWasFlooder()
            {
                return wasFlooder;
            }
            
            /**
             * @return wasConnected
             */
            @SuppressWarnings("unused")
            private boolean isWasConnected()
            {
                return wasConnected;
            }
            
            /**
             * Setter for HOST_LIST array
             * @param hOST_LIST ArrayList
             */
            public static void setHOST_LIST(ArrayList<String> hOST_LIST)
            {
                HOST_LIST = hOST_LIST;
            }
        
            /**
             * @return ArrayList HOST_LIST
             * @param getHOST_LIST ArrayList
             */
            public static ArrayList<String> getHOST_LIST()
            {
                return HOST_LIST;
            }
        
            /**
             * Setter for CONNECTIONS array
             * @param cONNECTIONS ArrayList
             */
            public static void setCONNECTIONS(ArrayList<String> cONNECTIONS)
            {
                CONNECTIONS = cONNECTIONS;
            }
        
            /**
             * @return ArrayList CONNECTIONS
             * @param getConnections ArrayList
             */
            public static ArrayList<String> getCONNECTIONS()
            {
                return CONNECTIONS;
            }
        
            /**
             * Adds a hostname to the HOST_LIST array list
             * @param host String
             */
            public void add(String host)
            {
                System.out.println((new StringBuilder("[ConnectionFilter] Temporarily banning ")).append(host).append(" (Massive Connecting Flooding)").toString());
                getHOST_LIST().add(host);
            }
        
            /**
             * Checks for any blocked hostnames
             * @param host String
             */
            public boolean blocked(String host)
            {
                for (String h : HOST_LIST)
                {
                    if (h.equals(host))
                        return true;
                }
                
                int n = 0;
                for (String h : CONNECTIONS)
                {
                    if (host.equals(h))
                    {
                        n++;
                    }
                }
                
                if (n > 2)
                {
                    add(host);
                    setFlooder(true);
                    return true;
                }
                
                if(isFlooder())
                    return true;
                
                if(isWasFlooder())
                {
                    /* Handle anything you want to do to a person that tryed flooding the server in the past. */
                }
                return false;
            }
        
            /**
             * 500ms Process, used for clearing out the array's after a given time in the cycle
             */
            public void process()
            {
                if(cycle % 10 == 0)
                {
                    getCONNECTIONS().clear();
                    setWasConnected(true);
                }
                if(cycle % 500 == 0)
                {
                    getHOST_LIST().clear();
                    setFlooder(false);
                    setWasFlooder(true);
                }
                if(cycle > 10000)
                {
                    cycle = 0;
                }
                cycle++;
            }
        
        }
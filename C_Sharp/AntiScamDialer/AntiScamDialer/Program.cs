using System;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;
using System.Text;
using System.Threading.Tasks;
using Twilio;

namespace AntiScamDialer
{
    class Program
    {
       public static String id = "*********";
        public static String auth = "*********";
        static void Main(string[] args)
        {
            Console.WriteLine("What number would you like to bombard with calls?");
            var outgoing = new Twilio.Types.PhoneNumber("+1" + Console.ReadLine());
            Console.WriteLine();
            Console.WriteLine("Press ENTER to initiate program.");
            Console.ReadLine();
            Console.WriteLine();
            Console.WriteLine();
            Console.WriteLine();
            Console.WriteLine("Calling " + outgoing + ". . .");

            int count = 1;
            while (count == 1)
            {
                TwilioClient.Init(id, auth);

                var from = new Twilio.Types.PhoneNumber("(a 'from' phone number)");
                var call = Twilio.Rest.Api.V2010.Account.CallResource.Create(outgoing, from, url: new Uri(
                    "http://demo.twilio.com/docs/voice.xml"));
                System.Threading.Thread.Sleep(5000);
            }
        }
    }
}

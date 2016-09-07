#include <string>
#include <iostream>
#include <winsock2.h>
#include <windows.h>
#include <sstream>


using namespace std;
template <typename T>
string NumberToString(T pNumber) //função que converte numeros para string
{
 ostringstream oOStrStream;
 oOStrStream << pNumber;
 return oOStrStream.str();
}

void HTTPReq( //função que inicia o pedido ao servidor
    const char* verb, //get ou post
    const char* hostname, //nome do servidor
    int port,
    const char* resource, //pagina especifica
    const char* opt_urlencoded, //argumentos a serem enviados para o site
    string& response)
{
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2,2), &wsaData) != 0)
    {
        cout << "WSAStartup failed.\n";
        exit(1);
    }

    SOCKET Socket = socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);

    struct hostent *host;
    host = gethostbyname(hostname);

    SOCKADDR_IN SockAddr;
    SockAddr.sin_port=htons(port);
    SockAddr.sin_family=AF_INET;
    SockAddr.sin_addr.s_addr = *((unsigned long*)host->h_addr);

    cout << "Connecting...\n";

    if (connect(Socket,(SOCKADDR*)(&SockAddr),sizeof(SockAddr)) != 0)
    {
        cout << "Could not connect";
        exit(1);
    }
    cout << "Connected.\n";

    // Build request
    string req = verb; // GET | POST
    req.append(" ");

    req.append(resource);
    req.append(" HTTP/1.1\r\n");

    req.append("Host: ");
    req.append(hostname);
    req.append(":");
    req.append(NumberToString(port));
    req.append("\r\n");

    if (strcmp(verb, "POST") == 0)
    {
        req.append("Cache-Control: no-cache\r\n");
        req.append("Content-length: ");
        req.append(NumberToString(strlen(opt_urlencoded)));
        req.append("\r\n");
        req.append("Content-Type: application/x-www-form-urlencoded\r\n\r\n");

        // User is required to handle URI encoding for this value
        req.append(opt_urlencoded);

    }
    else // default, GET
    {
        req.append("Cache-Control: no-cache\r\n");
        req.append("Connection: close\r\n\r\n");
    }

    cout << "=============================== request"
        << endl
        << req
        << endl
        << "=============================== "
        << endl;

    send(Socket, req.c_str(), req.size(), 0);

    char buffer[1024*10];
    int nlen;

    while ((nlen = recv(Socket,buffer,1024*10,0)) > 0)
    {
        response.append(buffer, 0, nlen);
    }
    closesocket(Socket);
    WSACleanup();

} // HTTPReq


int main()
{
    string response;

    HTTPReq("POST", "localhost", 90, "/pool/listener.php", "clr=0&ph=0&mac=1", response);

    cout << "=============================== response:"
        << endl
        << response
        << endl
        << "=============================== "
        << endl;

    /*
        Doing a POST, note the "percent" encoding for 'opt_urlencode':

    HTTPReq("POST", "soundly.me", 80, "/not-a-real-resource",
        "userdata=%7B%22key%22%3A%5B%22value0%22%5D%7D", response);

    */
    int num = 0;
    return num;
}

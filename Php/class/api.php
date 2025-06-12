<?php
class SimpleApiClient
{
    private $baseUrl;
    private $token = null;

    public function __construct($baseUrl)
    {
        $this->baseUrl = rtrim($baseUrl, '/');
    }

    public function setToken($jwt)
    {
        $this->token = $jwt;
    }

    private function request($method, $endpoint, $data = null)
    {
        $url = $this->baseUrl . '/' . ltrim($endpoint, '/');
        $ch = curl_init($url);

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, strtoupper($method));

        $headers = ['Content-Type: application/json'];
        if ($this->token) {
            $headers[] = 'Authorization: Bearer ' . $this->token;
        }
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

        if (in_array(strtoupper($method), ['POST', 'PUT', 'DELETE']) && $data !== null) {
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        }

       
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);

        $response = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        if (curl_errno($ch)) {
            $error = curl_error($ch);
            curl_close($ch);
            return [
                'status' => 'error',
                'message' => 'Erro cURL: ' . $error,
                'http_code' => $httpCode,
            ];
        }

        curl_close($ch);
        $decoded = json_decode($response, true);

        return [
            'status' => 'success',
            'http_code' => $httpCode,
            'data' => $decoded !== null ? $decoded : $response,
        ];
    }

    public function get($endpoint)
    {
        return $this->request('GET', $endpoint);
    }

    public function post($endpoint, $data)
    {
        return $this->request('POST', $endpoint, $data);
    }

    public function put($endpoint, $data)
    {
        return $this->request('PUT', $endpoint, $data);
    }

    public function delete($endpoint, $data = null)
    {
        return $this->request('DELETE', $endpoint, $data);
    }
}

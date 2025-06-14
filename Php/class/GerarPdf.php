<?php
require './vendor/autoload.php';


class CertificadoGenerator
{
    private $nomeEvento;
    private $nomeAluno;

    public function __construct($nomeEvento, $nomeAluno)
    {
        $this->nomeEvento = $nomeEvento;
        $this->nomeAluno = $nomeAluno;
    }

    public function gerar()
    {
        if (!$this->nomeEvento || !$this->nomeAluno) {
            flash()->push('error',"Erro ao gerar certificado");
            header("Location: dashboard");
            die();
            
        }

        $pdf = new FPDF('L', 'mm', 'A4');
        $pdf->AddPage();

    
        $logoPath = './imagens/logoAlfa.png';
        if (file_exists($logoPath)) {
            $pdf->Image($logoPath, 10, 10, 50);
        }

      
        $pdf->SetFont('Arial', 'B', 24);
        $pdf->Cell(0, 30, utf8_decode('CERTIFICADO DE PARTICIPAÇÃO'), 0, 1, 'C');

      
        $pdf->Ln(20);
        $pdf->SetFont('Arial', '', 18);
        $texto = "Certificamos que o(a) aluno(a) {$this->nomeAluno} participou do evento \"{$this->nomeEvento}\".";
        $pdf->MultiCell(0, 10, utf8_decode($texto), 0, 'C');

       
        $nomeArquivo = 'certificado_' . preg_replace('/\s+/', '_', $this->nomeAluno) . '.pdf';

       $pdf->Ln(30);
$pdf->SetFont('Arial', '', 14);


$larguraLinha = 60;
$posicaoX = 120; 
$posicaoY = 120;

// Linha de assinatura
$pdf->SetXY($posicaoX, $posicaoY);
$pdf->Cell($larguraLinha, 0, '', 'T'); 


$pdf->SetXY($posicaoX, $posicaoY + 5);
$pdf->Cell($larguraLinha, 10, utf8_decode("Rafael da Silva"), 0, 0, 'C');


        $pdf->Output('I', $nomeArquivo);
    }
}



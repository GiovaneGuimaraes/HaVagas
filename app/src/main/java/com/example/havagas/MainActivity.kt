package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var amb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(amb.root)

        amb.formacaoSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    in 0..1 -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.tituloMonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    in 2..3 -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloMonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    else -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloMonografiaEt.visibility = View.VISIBLE
                        amb.orientadorEt.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Não se aplica
            }
        }

        amb.celularRb.setOnClickListener {
            if (amb.celularEt.visibility == View.GONE) {
                amb.celularEt.visibility = View.VISIBLE
            } else {
                amb.celularEt.visibility = View.GONE
            }
        }

        amb.limparBt.setOnClickListener {
            amb.nomeEt.setText("")
            amb.emailEt.setText("")
            amb.telefoneEt.setText("")
            amb.celularEt.setText("")
            amb.celularEt.visibility = View.GONE
            amb.vagasEt.setText("")
            amb.emailRb.setChecked(false)
            amb.telefoneRg.check(amb.comercialRb.id)
            amb.sexoRg.check(amb.masculinoRb.id)
            amb.nascimentoTv.setText("")
            amb.formacaoSp.setSelection(0)
            amb.anoFormacaoEt.setText("")
            amb.instituicaoEt.setText("")
            amb.tituloMonografiaEt.setText("")
            amb.orientadorEt.setText("")
            amb.anoFormacaoEt.visibility = View.GONE
            amb.instituicaoEt.visibility = View.GONE
            amb.tituloMonografiaEt.visibility = View.GONE
            amb.orientadorEt.visibility = View.GONE
        }

        amb.salvaBt.setOnClickListener {
            val nome = amb.nomeEt.text.toString()
            val email = amb.emailEt.text.toString()
            val desejaReceberEmail = if (amb.emailRb.isChecked) "Sim" else "Não"
            val telefone = amb.telefoneEt.text.toString()
            val telefoneTipo = if (amb.comercialRb.isChecked) "Comercial" else "Residencial"
            val telefoneCelular = amb.celularEt.text.toString()
            val sexo = if (amb.masculinoRb.isChecked) "Masculino" else "Feminino"
            val data = amb.nascimentoTv.text.toString()
            val formacao = amb.formacaoSp.selectedItem.toString()
            val anoFormacao = amb.anoFormacaoEt.text.toString()
            val instituicao = amb.instituicaoEt.text.toString()
            val tituloMonografia = amb.tituloMonografiaEt.text.toString()
            val orientador = amb.orientadorEt.text.toString()
            val vagasInteresse = amb.vagasEt.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty()
                && telefone.isNotEmpty() && telefoneTipo.isNotEmpty()
                && sexo.isNotEmpty() && data.isNotEmpty() && formacao.isNotEmpty()
                && anoFormacao.isNotEmpty() && vagasInteresse.isNotEmpty()) {

                val mensagem = when (formacao) {
                    "Graduação", "Especialização" -> {
                        if (instituicao.isNotEmpty()) {
                            "nome: $nome \n email: $email \n receber email? $desejaReceberEmail \n " +
                                    "tipo telefone: $telefoneTipo \n telefone: $telefone \n Telefone celular: $telefoneCelular \n " +
                                    "sexo: $sexo \n nascimento: $data \n formação: $formacao \n ano de formação: $anoFormacao \n " +
                                    "instituição: $instituicao \n vagas de interesse: $vagasInteresse"
                        } else {
                            ""
                        }
                    }
                    "Mestrado", "Doutorado" -> {
                        if (tituloMonografia.isNotEmpty() && orientador.isNotEmpty()) {
                            "nome: $nome \n email: $email \n receber email? $desejaReceberEmail \n " +
                                    "tipo telefone: $telefoneTipo \n telefone: $telefone \n telefone celular: $telefoneCelular \n " +
                                    "sexo: $sexo \n nascimento: $data \n formação: $formacao \n ano de formação: $anoFormacao \n " +
                                    "instituição: $instituicao \n título monografia: $tituloMonografia \n " +
                                    "orientador: $orientador \n vagas de interesse: $vagasInteresse"
                        } else {
                            ""
                        }
                    } else -> {
                        "nome: $nome \n email: $email \n receber email? $desejaReceberEmail \n " +
                                "tipo telefone: $telefoneTipo \n telefone: $telefone \n telefone celular: $telefoneCelular \n " +
                                "sexo: $sexo \n nascimento: $data \n formação: $formacao \n ano de formação: $anoFormacao \n " +
                                "vagas de interesse: $vagasInteresse"
                    }
            }
                if (mensagem.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, mensagem, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
            }

        }
    }
}